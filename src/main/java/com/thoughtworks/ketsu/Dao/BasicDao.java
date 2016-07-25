package com.thoughtworks.ketsu.Dao;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.net.UnknownHostException;

public class BasicDao {
    private static DB db;

    public static DB getDB()  {
        if(db == null) {
            MongoClient mongoClient = null;
            try {
                mongoClient = new MongoClient(
                        new MongoClientURI("mongodb://admin:mypass@localhost/mongodb_store"));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            db = mongoClient.getDB("mongodb_store");
        }
        return db;
    }
}
