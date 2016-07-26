package com.thoughtworks.ketsu.domain.users;

import com.mongodb.DBObject;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

public class User implements Record{
    private String _id;
    private String name;

    public User(DBObject object) {
        this._id = object.get("_id").toString();
        this.name = object.get("name").toString();
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap() {{
            put("uri", routes.userUrl(get_id()));
            put("id", get_id());
            put("name", getName());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
