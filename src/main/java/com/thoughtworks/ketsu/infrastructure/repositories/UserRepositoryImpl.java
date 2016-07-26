package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.users.User;
import com.thoughtworks.ketsu.domain.users.UserRepository;
import com.thoughtworks.ketsu.infrastructure.mongo.mappers.UserMapper;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Inject
    UserMapper userMapper;
    @Override
    public User save(Map info) {
        return userMapper.save(info);
    }

    @Override
    public Optional<User> findById(ObjectId id) {
        return Optional.ofNullable(userMapper.findById(id));
    }
}
