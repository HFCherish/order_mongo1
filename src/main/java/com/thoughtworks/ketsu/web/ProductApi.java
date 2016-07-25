package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;
import com.thoughtworks.ketsu.web.validators.FieldNotNullValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Path("products")
public class ProductApi {
    @Context
    ProductRepository productRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Map prodInfo,
                           @Context Routes routes) {
        Map<String, List> nullFields = new FieldNotNullValidator().getNullFields(Arrays.asList("name", "description", "price"), prodInfo);
        if(nullFields.get("items").size() > 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity(nullFields).build();
        }

        Product product = productRepository.save(prodInfo);

        return Response.created(routes.productUrl(product.get_id())).build();
    }

//    @GET
//    @Path("{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Product getOne(@PathParam("id") String id) {
//        return productRepository.findById(id)
//                .map(product -> product)
//                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Product> getAll() {
//        return productRepository.findAll();
//    }
}
