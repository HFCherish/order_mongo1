package com.thoughtworks.ketsu.domain.users;

import com.mongodb.DBObject;

import java.util.List;

public class Order {
    private String id;
    private String name;
    private String address;
    private String phone;
    private List<OrderItem> orderItems;

    public Order(DBObject object) {
        this.id = object.get("_id").toString();
        this.name = object.get("name").toString();
        this.address = object.get("address").toString();
        this.phone = object.get("phone").toString();
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
}
