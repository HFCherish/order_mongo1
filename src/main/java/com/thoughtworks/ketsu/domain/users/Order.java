package com.thoughtworks.ketsu.domain.users;

import com.mongodb.DBObject;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.bson.types.ObjectId;

import java.util.*;

public class Order implements Record{
    private String id;
    private String userId;
    private String name;
    private String address;
    private String phone;
    private List<OrderItem> orderItems;

    public Order(DBObject object) {
        this.id = object.get("_id").toString();
        this.userId = object.get("user_id").toString();
        this.name = object.get("name").toString();
        this.address = object.get("address").toString();
        this.phone = object.get("phone").toString();
        orderItems = new ArrayList<>();
        List<Map> items = (List)object.get("order_items");
        for(Map item: items) {
            orderItems.add(new OrderItem(item));
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        List<Map> items = new ArrayList<>();
        for(OrderItem item: getOrderItems()) {
            items.add(item.toJson(routes));
        }

        return new HashMap() {{
            put("uri", routes.orderUrl(getUserId(), getId()));
            put("name", getName());
            put("address", getAddress());
            put("phone", getPhone());
            put("created_at", getCreatedAt().toString());
            put("order_items", items);
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }

    public Date getCreatedAt() {
        return new ObjectId(getId()).getDate();
    }
}
