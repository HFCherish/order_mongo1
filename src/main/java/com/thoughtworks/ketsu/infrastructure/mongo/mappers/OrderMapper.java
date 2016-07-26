package com.thoughtworks.ketsu.infrastructure.mongo.mappers;

import com.thoughtworks.ketsu.domain.users.Order;

import java.util.Map;

public interface OrderMapper {
    Order save(Map orderInfo);

    Order findById(String id);
}
