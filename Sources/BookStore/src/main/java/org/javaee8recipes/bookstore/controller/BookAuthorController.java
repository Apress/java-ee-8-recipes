/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.bookstore.controller;

import java.util.List;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.ws.rs.Path;
import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import org.javaee8recipes.bookstore.entity.BookAuthor;
import org.javaee8recipes.bookstore.service.BookAuthorService;

/**
 *
 * @author Juneau
 */
@Path("/bookAuthor")
@Controller()
public class BookAuthorController {
    
    @Inject
    private Models models;
    
    @Inject
    private BookAuthorService bookAuthorService;
    
    public BookAuthorController(){
    }
    
    @GET
    public String getBookAuthors(){
        List<BookAuthor> bookAuthors = bookAuthorService.getBookAuthorList();
        models.put("bookauthors", bookAuthors);
        return "bookAuthor.jsp";
    }
}
