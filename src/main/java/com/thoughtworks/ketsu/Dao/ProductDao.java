package com.thoughtworks.ketsu.Dao;

import com.mongodb.*;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.infrastructure.mongo.mappers.ProductMapper;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDao implements ProductMapper {


    private static final Logger logger = LoggerFactory.getLogger(ProductDao.class);
    private DBCollection prodsCollection;

    @Inject
    public ProductDao(DB db) {
        prodsCollection = db.getCollection("products");
    }

    @Override
    public Product save(final Map prodToCreate) {
        BasicDBObject prodDBObject = new BasicDBObject(prodToCreate);
        prodsCollection.insert(prodDBObject);
        logger.info("Added new product{}", prodToCreate);

        return buildProduct(prodsCollection.findOne());
    }

    @Override
    public Product findById(ObjectId id) {
        DBObject query = new BasicDBObject("_id", id);
        DBObject prod = prodsCollection.findOne(query);

        if (prod != null) {
            return buildProduct(prod);
        }
        return null;
    }


    @Override
    public List<Product> findAll() {
        List<Product> prods = new ArrayList<>();

        DBCursor dbObjects = prodsCollection.find();
        while (dbObjects.hasNext()) {
            prods.add(buildProduct(dbObjects.next()));
        }

        return prods;
    }

    private Product buildProduct(DBObject object) {
        return new Product((ObjectId) object.get("_id"),
                object.get("name").toString(),
                object.get("description").toString(),
                (double) object.get("price"));
    }
}
