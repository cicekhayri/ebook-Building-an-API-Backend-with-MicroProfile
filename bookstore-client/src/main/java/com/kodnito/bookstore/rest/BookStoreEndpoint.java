package com.kodnito.bookstore.rest;

import java.net.MalformedURLException;
import java.net.URL;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import com.kodnito.bookstore.service.BookStoreService;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Path("/books")
public class BookStoreEndpoint {

    @Inject
    @ConfigProperty(name = "book-store.base-url")
    private String bookStoreBaseUrl;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response books() throws MalformedURLException {
        URL url = new URL(bookStoreBaseUrl);
        BookStoreService bookStoreService
                = RestClientBuilder.newBuilder()
                        .baseUrl(url)
                        .build(BookStoreService.class);

        return Response.ok(bookStoreService.getAll()).build();
    }
}
