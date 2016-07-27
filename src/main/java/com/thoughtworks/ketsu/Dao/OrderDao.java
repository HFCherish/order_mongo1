package com.thoughtworks.ketsu.Dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.thoughtworks.ketsu.domain.users.Order;
import com.thoughtworks.ketsu.infrastructure.mongo.mappers.OrderMapper;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.Map;

public class OrderDao implements OrderMapper {

    private final DBCollection orderCollection;

    @Inject
    public OrderDao(DB db) {
        orderCollection = db.getCollection("orders");
    }

    @Override
    public Order save(Map orderInfo, String userId) {
        orderInfo.put("user_id", userId);
        orderCollection.insert(new BasicDBObject(orderInfo));
        return new Order(orderCollection.findOne());
    }

    @Override
    public Order findById(String id) {
        DBObject orderObject = orderCollection.findOne(new BasicDBObject("_id", new ObjectId(id)));
        System.out.println("******************"+orderObject);
        return orderObject == null ? null : new Order(orderObject);
    }
}
