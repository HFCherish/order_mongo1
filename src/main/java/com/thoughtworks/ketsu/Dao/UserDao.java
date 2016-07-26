package com.thoughtworks.ketsu.Dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.thoughtworks.ketsu.domain.users.User;
import com.thoughtworks.ketsu.infrastructure.mongo.mappers.UserMapper;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.Map;

public class UserDao implements UserMapper {

    private final DBCollection userCollection;

    @Inject
    public UserDao(DB db) {
        userCollection = db.getCollection("users");
    }


    @Override
    public User save(Map info) {
        userCollection.insert(new BasicDBObject(info));
        return new User(userCollection.findOne());
    }

    @Override
    public User findById(String id) {
        DBObject user = userCollection.findOne(new BasicDBObject("_id", new ObjectId(id)));

        return user == null ? null : new User(user);
    }
}
