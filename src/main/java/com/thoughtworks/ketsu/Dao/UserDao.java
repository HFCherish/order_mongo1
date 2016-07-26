package com.thoughtworks.ketsu.Dao;

import com.google.inject.Injector;
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
    Injector injector;

    @Inject
    public UserDao(DB db) {
        userCollection = db.getCollection("users");
    }


    @Override
    public User save(Map info) {
        userCollection.insert(new BasicDBObject(info));

        User user = new User(userCollection.findOne());
        injector.injectMembers(user);
        return user;
    }

    @Override
    public User findById(String id) {
        DBObject user = userCollection.findOne(new BasicDBObject("_id", new ObjectId(id)));
        if(user != null) {
            User user1 = new User(user);
            injector.injectMembers(user1);
            return user1;
        }
        return null;
    }
}
