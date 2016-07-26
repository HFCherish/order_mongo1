package com.thoughtworks.ketsu.web.jersey;

import org.bson.types.ObjectId;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = uriInfo.getBaseUri().toASCIIString();
    }

    public URI productUrl(ObjectId id) {
        return URI.create(baseUri + "products/" + id);
    }

    public URI userUrl(String id) {
        return URI.create(baseUri + "users/" + id);
    }

    public URI orderUrl(long userId, long orderId) {
        return URI.create(baseUri + "users/" + userId + "/orders/" + orderId);
    }

    public URI paymentUrl(long userId, long orderId) {
        return URI.create(baseUri + "users/" + userId + "/orders/" + orderId + "/payment");
    }
}
