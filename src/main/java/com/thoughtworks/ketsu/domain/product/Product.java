package com.thoughtworks.ketsu.domain.product;

import com.mongodb.DBObject;
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

    public Product(DBObject object) {
        this._id = (ObjectId) object.get("_id");
        this.name = object.get("name").toString();
        this.description = object.get("description").toString();
        this.price = (double)object.get("price");
    }

    //    @XmlTransient
    public ObjectId get_id() {
        return _id;
    }

//    @XmlElement(name = "name")
    public String getName() {
        return name;
    }
//    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }
//    @XmlElement(name = "price")
    public double getPrice() {
        return price;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("id", get_id().toString());
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
