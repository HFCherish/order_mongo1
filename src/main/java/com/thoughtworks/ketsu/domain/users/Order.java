package com.thoughtworks.ketsu.domain.users;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Order implements Record {
    private String id;
    private String userId;
    private String name;
    private String address;
    private String phone;
    private List<OrderItem> orderItems;

    public Order(String id, String userId, String name, String address, String phone, List<OrderItem> orderItems) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.orderItems = orderItems;
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
        return new HashMap() {{
            put("uri", routes.orderUrl(getUserId(), getId()));
            put("name", getName());
            put("address", getAddress());
            put("phone", getPhone());
            put("total_price", getTotalPrice());
            put("created_at", getCreatedAt().toString());
            put("order_items", getOrderItems().stream().map(item -> item.toJson(routes)).collect(Collectors.toList()));
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }

    public Date getCreatedAt() {
        return new ObjectId(getId()).getDate();
    }

    public double getTotalPrice() {
        double total = 0;
        for (OrderItem item : orderItems)
            total += item.getAmount() * item.getQuantity();
        return total;
    }
}
