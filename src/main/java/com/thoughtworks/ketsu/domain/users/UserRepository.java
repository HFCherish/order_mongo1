package com.thoughtworks.ketsu.domain.users;

import org.bson.types.ObjectId;

import java.util.Map;
import java.util.Optional;

public interface UserRepository {
    User save(Map info);

    Optional<User> findById(String id);
}
