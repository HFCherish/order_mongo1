package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.users.Order;
import com.thoughtworks.ketsu.domain.users.User;
import com.thoughtworks.ketsu.domain.users.UserRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestHelper {

    public static final String USER_NAME = "Petrina";
    public static final String INVALID_USER_NAME = "8098DJ;Petrina";

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

    public static Map<String, Object> orderJsonForTest(String prodId) {
        return new HashMap<String, Object>() {{
            put("name", USER_NAME);
            put("address", "beijing");
            put("phone", "68790");
            put("order_items", Arrays.asList(new HashMap() {{
                put("product_id", prodId);
                put("quantity", 2);
            }}));
        }};
    }

    public static Order prepareOrder(User user, Product product) {
        return user.placeOrder(orderJsonForTest(product.get_id().toString()));
    }

    public static Map<String, Object> userJsonForTest(String name) {
        return new HashMap<String, Object>() {{
            put("name", name);
        }};
    }

    public static User prepareUser(UserRepository userRepository) {
        return userRepository.save(userJsonForTest(USER_NAME));
    }
}
