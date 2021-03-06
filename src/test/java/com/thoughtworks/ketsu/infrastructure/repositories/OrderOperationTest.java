package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.users.Order;
import com.thoughtworks.ketsu.domain.users.User;
import com.thoughtworks.ketsu.domain.users.UserRepository;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(DatabaseTestRunner.class)
public class OrderOperationTest {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;
    private User user;
    private Product product;

    @Before
    public void setUp() {
        user = prepareUser(userRepository);
        product = prepareProduct(productRepository);
    }

    @Test
    public void should_save_and_get_order() {
        Order order = user.placeOrder(orderJsonForTest(product.get_id().toString()));
        Optional<Order> fetched = user.findOrderById(order.getId());

        assertThat(fetched.isPresent(), is(true));
        assertThat(fetched.get().getId(), is(order.getId()));
    }

    @Test
    public void should_get_all_orders() {
        Order order = prepareOrder(user,product);

        List<Order> allOrders = user.findAllOrders();

        assertThat(allOrders.size(), is(1));
        assertThat(allOrders.get(0).getId(), is(order.getId()));
    }
}
