package com.kodnito.bookstore.rest;

import com.kodnito.bookstore.entity.Book;
import com.kodnito.bookstore.service.BookService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;

@RequestScoped
@Path("books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookStoreEndpoint {

    @Inject
    BookService bookService;

    @GET
    public Response getAll() {
        return Response.ok(bookService.getAll()).build();
    }

    @Counted(unit = MetricUnits.NONE,
            name = "getBook",
            absolute = true,
            monotonic = true,
            displayName = "get single book",
            description = "Monitor how many times getBook method was called")
    @GET
    @Path("{id}")
    public Response getBook(@PathParam("id") Long id) {
        Book book = bookService.findById(id);

        return Response.ok(book).build();
    }

    @Metered(name = "create-books",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor the rate events occured",
            absolute = true)
    @POST
    public Response create(Book book) {
        bookService.create(book);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Book book) {
        Book updateBook = bookService.findById(id);

        updateBook.setIsbn(book.getIsbn());
        updateBook.setDescription(book.getDescription());
        updateBook.setLanguage(book.getLanguage());
        updateBook.setPages(book.getPages());
        updateBook.setPrice(book.getPrice());
        updateBook.setPublisher(book.getPublisher());
        updateBook.setTitle(book.getTitle());

        bookService.update(updateBook);

        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Book getBook = bookService.findById(id);
        bookService.delete(getBook);
        return Response.ok().build();
    }
}
