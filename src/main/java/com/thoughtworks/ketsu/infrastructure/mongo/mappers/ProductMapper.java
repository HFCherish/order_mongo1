package com.thoughtworks.ketsu.infrastructure.mongo.mappers;

import com.thoughtworks.ketsu.domain.product.Product;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public interface ProductMapper {

    Product save(Map prodInfo);

    Product findById(ObjectId id);

    List<Product> findAll();
}
