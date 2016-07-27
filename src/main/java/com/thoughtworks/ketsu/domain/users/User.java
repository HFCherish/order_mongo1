package com.thoughtworks.ketsu.domain.users;

import com.mongodb.DBObject;
import com.thoughtworks.ketsu.infrastructure.mongo.mappers.OrderMapper;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.inject.Inject;
import java.util.*;

public class User implements Record{
    private String _id;
    private String name;

    @Inject
    OrderMapper orderMapper;

    public User(String _id, String name) {
        this._id = _id;
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap() {{
            put("uri", routes.userUrl(get_id()));
            put("id", get_id());
            put("name", getName());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }

    public Order placeOrder(Map orderInfo) {
        return orderMapper. save(orderInfo, get_id());
    }

    public Optional<Order> findOrderById(String id) {
        return Optional.ofNullable(orderMapper.findById(id));
    }

    public List<Order> findAllOrders() {
        return orderMapper.findAllOf(get_id());
    }
}
