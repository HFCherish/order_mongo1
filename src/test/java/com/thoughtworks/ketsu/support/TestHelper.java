package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class TestHelper {

    public static final String USER_NAME = "Petrina";

    public static Product prepareProduct(ProductRepository productRepository) {
        return productRepository.save(productJsonForTest());
    }

    public static Map<String, Object> productJsonForTest() {
        return new HashMap<String, Object>() {{
            put("name", "Imran");
            put("description", "teachers");
            put("price", 1.1);
        }};
    }

    public static Map<String, Object> userJsonForTest(String name) {
        return new HashMap<String, Object>() {{
            put("name", name);
        }};
    }

}
