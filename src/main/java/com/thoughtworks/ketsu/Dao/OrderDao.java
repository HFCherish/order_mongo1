package com.thoughtworks.ketsu.Dao;

import com.mongodb.*;
import com.thoughtworks.ketsu.domain.users.Order;
import com.thoughtworks.ketsu.domain.users.OrderItem;
import com.thoughtworks.ketsu.infrastructure.mongo.mappers.OrderMapper;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDao implements OrderMapper {

    private final DBCollection orderCollection;

    @Inject
    ProductDao productDao;

    @Inject
    public OrderDao(DB db) {
        orderCollection = db.getCollection("orders");
    }

    @Override
    public Order save(Map orderInfo, String userId) {
        orderInfo.put("user_id", userId);
        for(Map item: (List<Map>)orderInfo.get("order_items")) {
            item.put("amount", productDao.findById(new ObjectId(item.get("product_id").toString())).getPrice());
        }
        orderCollection.insert(new BasicDBObject(orderInfo));

        return buildOrder(orderCollection.findOne());
    }

    @Override
    public Order findById(String id) {
        DBObject orderObject = orderCollection.findOne(new BasicDBObject("_id", new ObjectId(id)));
        return orderObject == null ? null : buildOrder(orderObject);
    }

    @Override
    public List<Order> findAllOf(String userId) {
        DBCursor orderObjects = orderCollection.find(new BasicDBObject("user_id", userId));
        List<Order> orders = new ArrayList<>();
        while(orderObjects.hasNext()) {
            orders.add(buildOrder(orderObjects.next()));
        }
        return orders;
    }


    private Order buildOrder(DBObject object) {
        return new Order(object.get("_id").toString(),
                object.get("user_id").toString()
                , object.get("name").toString()
                , object.get("address").toString()
                , object.get("phone").toString()
                , buildOrderItems(object));

    }

    private List<OrderItem> buildOrderItems(DBObject object) {
        List<OrderItem> orderItems = new ArrayList<>();
        List<Map> items = (List) object.get("order_items");
        orderItems.addAll(items.stream().map(item -> new OrderItem(item.get("product_id").toString(),
                (int) item.get("quantity"),
                (double) item.get("amount"))).collect(Collectors.toList()));
        return orderItems;
    }
}
