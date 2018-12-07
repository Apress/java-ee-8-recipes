/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.bookstore.service;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import org.javaee8recipes.bookstore.entity.BookAuthor;

/**
 *
 * @author Juneau
 */
@Named
@SessionScoped
public class BookAuthorService implements java.io.Serializable {
    Client jaxRsClient;
    private List<BookAuthor> bookAuthorList;
    
    
    String hostUri = "http://localhost:8080/BookStore/bookstore";
    
    public BookAuthorService(){
        
    }
    
    @PostConstruct
    public void init(){
        // Construct a JAX-RS Client
        jaxRsClient = ClientBuilder.newClient();
        loadData();
    }
    
    private void loadData(){
            
        bookAuthorList = jaxRsClient.target(hostUri + "/org.javaee8recipes.bookstore.entity.bookauthor/findAll")
                .request("application/xml")
                .get(new GenericType<List<BookAuthor>>() {
                }
                );
    }
    

    /**
     * @return the bookAuthorList
     */
    public List<BookAuthor> getBookAuthorList() {
        if(bookAuthorList == null){
             loadData();
        }
        return bookAuthorList;
    }

    /**
     * @param bookAuthorList
     */
    public void setBookAuthorList(List<BookAuthor> bookAuthorList) {
        this.bookAuthorList = bookAuthorList;
    }
}
