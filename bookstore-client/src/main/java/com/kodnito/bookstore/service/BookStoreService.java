package com.kodnito.bookstore.service;

import com.kodnito.bookstore.response.BookResponse;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Dependent
@RegisterRestClient
@Path("books")
@Produces(MediaType.APPLICATION_JSON)
public interface BookStoreService {
        
    @GET
    public List<BookResponse> getAll();
}