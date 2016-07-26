package com.thoughtworks.ketsu.web;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.bson.types.ObjectId;
import org.immutables.value.internal.$processor$.meta.$MongoMirrors;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.prepareProduct;
import static com.thoughtworks.ketsu.support.TestHelper.productJsonForTest;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class ProductApiTest extends ApiSupport {
    private String productBaseUrl = "/products";

    @Inject
    ProductRepository productRepository;

    @Test
    public void should_create_product() {
        Response response = post(productBaseUrl, productJsonForTest());

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(productBaseUrl));
        assertThat(response.getLocation().toString().matches(".*/[a-zA-Z\\d]+$"), is(true));
    }

    @Test
    public void should_400_when_creat_given_incomplet_params() {
        Response response = post(productBaseUrl, new HashMap());

        assertThat(response.getStatus(), is(400));
        Map map = response.readEntity(Map.class);
        assertThat(map.get("items"), is(notNullValue()));
        List errors = (List) map.get("items");
        assertThat(errors.size(), is(3));
        Map nameError = (Map)errors.get(0);
        assertThat(nameError.get("field"), is("name"));
        assertThat(nameError.get("message").toString(), containsString("name can not be empty"));

    }

    @Test
    public void should_get_some_product() {
        Product product = prepareProduct(productRepository);
        String getOneUrl = productBaseUrl + "/" + product.get_id();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(200));
        Map info = response.readEntity(Map.class);
        verifyProductInfo(product, info);
    }

    private void verifyProductInfo(Product product, Map info) {
        assertThat(info.get("id").toString(), is(product.get_id().toString()));
        assertThat(info.get("uri").toString(), containsString(productBaseUrl + "/" + product.get_id()));
        assertThat(info.get("name"), is(product.getName()));
        assertThat((double)info.get("price"), is(product.getPrice()));
        assertThat(info.get("description"), is(product.getDescription()));
    }

    @Test
    public void should_404_when_get_product_given_not_exist() {
        Product product = prepareProduct(productRepository);
        String getOneUrl = productBaseUrl + "/" + new ObjectId();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(404));

    }

    @Test
    public void should_get_all_products() {
        Product product = prepareProduct(productRepository);

        Response response = get(productBaseUrl);

        assertThat(response.getStatus(), is(200));
        List prods = response.readEntity(List.class);
        assertThat(prods.size(), is(1));
        verifyProductInfo(product, (Map)prods.get(0));
    }
}
