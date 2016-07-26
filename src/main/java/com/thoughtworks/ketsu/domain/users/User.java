package com.thoughtworks.ketsu.domain.users;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;

public class User {
    private ObjectId _id;
    private String name;

    public User(DBObject object) {
        this._id = (ObjectId)object.get("_id");
        this.name = object.get("name").toString();
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }
}
