package com.thoughtworks.ketsu.Dao;

import com.mongodb.*;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.util.AppUtils.fromDBObject;

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
//        BasicDBObject prodDBObject = new BasicDBObject();
//        prodDBObject.put("name", prodToCreate.)
        prodsCollection.insert(prodDBObject);
        logger.info("Added new product{}", prodToCreate);

        ObjectId id = (ObjectId) prodDBObject.get("_id");
        return findById(id);
    }

    @Override
    public Product findById(ObjectId id) {
        DBObject query = new BasicDBObject("_id", id);
        DBObject prod = prodsCollection.findOne(query);

        if(prod != null) {
            return new Product(prod);
        }
        return null;
    }


    @Override
    public List<Product> findAll() {
        return null;
    }


//    public Optional<Book> readByIsbn(@Nonnull final String isbn) {
//        checkNotNull(isbn, "Argument[isbn] must not be null");
//
//        DBObject query = new BasicDBObject("isbn", isbn);
//        DBObject dbObject = booksCollection.findOne(query);
//
//        if (dbObject != null) {
//            Book book = (Book) AppUtils.fromDBObject(dbObject, Book.class);
//            logger.info("Retrieved book for isbn[{}]:{}", isbn, book);
//            return Optional.of(book);
//        }
//        logger.info("Book with isbn[{}] does not exist", isbn);
//        return Optional.empty();
//    }

}
