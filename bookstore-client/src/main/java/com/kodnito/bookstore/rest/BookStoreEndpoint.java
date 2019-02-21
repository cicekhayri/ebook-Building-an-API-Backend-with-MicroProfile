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
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/books")
public class BookStoreEndpoint {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response books() throws MalformedURLException {
        URL url = new URL("http://localhost:8080/restapi");
        BookStoreService bookStoreService
                = RestClientBuilder.newBuilder()
                        .baseUrl(url)
                        .build(BookStoreService.class);

        return Response.ok(bookStoreService.getAll()).build();
    }
}
