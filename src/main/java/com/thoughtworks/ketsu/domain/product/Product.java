package com.thoughtworks.ketsu.domain.product;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

public class Product implements Record{

    private ObjectId _id;
    private String name;
    private String description;
    private double price;

    public Product(ObjectId _id, String name, String description, double price) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("_id", get_id().toString());
            put("uri", routes.productUrl(get_id()));
            put("name", getName());
            put("description", getDescription());
            put("price", getPrice());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
