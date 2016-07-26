package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.users.User;
import com.thoughtworks.ketsu.web.jersey.Routes;
import com.thoughtworks.ketsu.web.validators.FieldNotNullValidator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrdersApi {
    private User user;

    public OrdersApi(User user) {
        this.user = user;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buildOrder(Map orderInfo,
                               @Context Routes routes) {
        Map<String, List> nullFields = new FieldNotNullValidator().getNullFields(Arrays.asList("name", "address", "phone"), orderInfo);
        if (nullFields != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(nullFields).build();
        }
        return Response.created(routes.orderUrl(user.get_id(), user.placeOrder(orderInfo).getId())).build();
    }
}
