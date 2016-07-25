package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.productJsonForTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(DatabaseTestRunner.class)
public class ProductRepositoryTest {
    @Inject
    ProductRepository productRepository;

    @Test
    public void should_save_and_get_product() {

        Product product = productRepository.save(productJsonForTest());
        Optional<Product> fetched = productRepository.findById(product.get_id());

        assertThat(fetched.isPresent(), is(true));
        assertThat(fetched.get().get_id(), is(product.get_id()));
    }

//    @Test
//    public void should_get_all() {
//        Product product = prepareProduct(productRepository);
//
//        List<Product> fetched = productRepository.findAll();
//
//        assertThat(fetched.size(), is(1));
//        assertThat(fetched.get(0).get_id(), is(product.get_id()));
//    }
}
