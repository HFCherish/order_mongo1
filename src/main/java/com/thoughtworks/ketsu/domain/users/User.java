package com.thoughtworks.ketsu.domain.users;

import org.bson.types.ObjectId;

public class User {
    private ObjectId _id;
    private String name;

    public ObjectId get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }
}
