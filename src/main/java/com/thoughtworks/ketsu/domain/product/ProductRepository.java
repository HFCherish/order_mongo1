package com.thoughtworks.ketsu.domain.product;

import org.bson.types.ObjectId;

import java.util.Map;
import java.util.Optional;

public interface ProductRepository {
    Product save(Map prodInfo);

    Optional<Product> findById(ObjectId id);

//    List<Product> findAll();
}
