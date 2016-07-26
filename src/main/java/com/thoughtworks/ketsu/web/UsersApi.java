package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Map;

@Path("users")
public class UsersApi {
//    @Context
//    UserRepository userRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(Map info,
                             @Context Routes routes) {
//        if( info.get("name") == null || !info.get("name").toString().matches("^[a-zA-Z\\d]+$")) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(new HashMap
//                    <String, String>() {{
//                put("field", "name");
//                put("message", "name can not be empty and must composed of letters and numbers");
//            }}).build();
//        }
//        return Response.created(routes.userUrl(userRepository.save(info).getId())).build();
        return Response.created(URI.create("")).build();
    }

//    @Path("{userId}")
//    public UserApi getUser(@PathParam("userId") long userId) {
//        return userRepository.findById(userId)
//                .map(UserApi::new)
//                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
//    }
}
