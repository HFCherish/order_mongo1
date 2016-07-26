package com.thoughtworks.ketsu.infrastructure.mongo.mappers;

import com.thoughtworks.ketsu.domain.users.User;

import java.util.Map;

public interface UserMapper {
    User save(Map info);

    User findById(String id);
}
