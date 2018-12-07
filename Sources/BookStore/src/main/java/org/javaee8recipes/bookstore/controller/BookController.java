/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.bookstore.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.javaee8recipes.bookstore.container.BookContainer;
import org.javaee8recipes.bookstore.container.Messages;
import org.javaee8recipes.bookstore.entity.Book;
import org.javaee8recipes.bookstore.service.BookService;

/**
 *
 * @author Juneau
 */
@Path("/book")
@Controller()
public class BookController {
    
    @Inject
    private BookService bookService;
    
    @Inject
    private Models models;
    
    @Inject
    private BookContainer bookContainer;
    
    @Inject
    private Messages messages;
    
    private List<Book> bookList;
    
    public BookController() {
        
    }
    
    /**
     * Queries all books using the <code>BookService</code> and then
     * returns to the <code>book.jsp</code> JSP page.
     * @return 
     */
    @GET
    public String getBooks(){
        Book book = new Book();
        book.setTitle("Java EE 8 Recipes");
        bookContainer.setBook(book);
        book.setDescription("Java EE 8 Recipes allows you to learn what you need to get the job done and be home in time for dinner.");
        return "book.jsp";
    }
    
    /**
     * Queries all books and returns the listing to books.xhtml view.
     * @return 
     */
    @GET
    @Path("/list")
    public String getListFacelets(){
        bookList = bookService.getBookList();
        bookContainer.setBookList(bookList);
        messages.setInfo("There are " + bookList.size() + " books in the library.");
        System.out.println("Issue count: " + bookList.size());
        return "books.xhtml";
    }
    
    @GET
    @Path("/pebbleTest")
    public String viewPebbleTest(){
        models.put("websiteTitle", "Pebble Template Tester");
        models.put("content","This is the first Pebble view");
        return "pebbleTest.html";
    }
    
    @POST
    @Path("/create")
    @Controller
    public String createItem(@BeanParam @Valid Book form) {
        // Create new book
        
        // Obtain issue list to count records for ID population
        bookList = bookService.getBookList();
        form.setId(new BigDecimal(bookList.size() + 1));
        Book entity = new Book();
        entity.setId(form.getId());
        entity.setTitle(form.getTitle());
        entity.setDescription(form.getDescription());

        bookService.create(entity);
        
        return displayBookListing();

    }
    
    @GET
    @Path("/books")
    public String displayBookListing() {
        bookList = bookService.getBookList();
        bookContainer.setBookList(bookList);
        messages.setInfo("There are " + bookList.size() + " books in the library.");
        System.out.println("Issue count: " + bookList.size());
        return "book.jsp";
    }
    
}
