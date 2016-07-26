package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.mongo.mappers.ProductMapper;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
    @Inject
    ProductMapper productMapper;

    @Override
    public Product save(Map prodInfo) {
        return productMapper.save(prodInfo);
    }

    @Override
    public Optional<Product> findById(ObjectId id) {
        return Optional.ofNullable(productMapper.findById(id));
    }

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }
}
