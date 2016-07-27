package com.thoughtworks.ketsu.domain.users;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

public class OrderItem implements Record {
    private String prodId;
    private int quantity;
    private double amount;

    public OrderItem(String prodId, int quantity, double amount) {
        this.prodId = prodId;
        this.quantity = quantity;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getProdId() {
        return prodId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap() {{
            put("product_id", getProdId());
            put("quantity", getQuantity());
            put("amount", getAmount());
            put("uri", routes.productUrl(new ObjectId(prodId)));
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
