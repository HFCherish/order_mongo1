package com.thoughtworks.ketsu.Dao;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.net.UnknownHostException;

public class BasicDao {
    private static DB db;

    private static String getConnectUrl() {
        String dbname = System.getenv().getOrDefault("MONGODB_DATABASE", "mongodb_store");
        String host = System.getenv().getOrDefault("MONGODB_HOST", "localhost");
        String port = System.getenv().getOrDefault("MONGODB_PORT", "27017");
        String username = System.getenv().getOrDefault("MONGODB_USER", "admin");
        String password = System.getenv().getOrDefault("MONGODB_PASS", "mypass");
        String connectURL = String.format(
                "mongodb://%s:%s@%s/%s",
                username,
                password,
                host,
                dbname
        );
        return connectURL;
    }

    public static DB getDB()  {
        if(db == null) {
            MongoClient mongoClient = null;
            try {
                mongoClient = new MongoClient(
                        new MongoClientURI(getConnectUrl()));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            db = mongoClient.getDB("mongodb_store");
        }
        return db;
    }
}
