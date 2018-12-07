/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.bookstore.service;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import org.javaee8recipes.bookstore.entity.Book;

/**
 *
 * @author Juneau
 */
@Named
@SessionScoped
public class BookService implements java.io.Serializable {
    
    @EJB
    private BookFacadeREST bookFacade;
    
    Client jaxRsClient;
    private List<Book> bookList;
    
    
    String hostUri = "http://localhost:8080/BookStore/bookstore";
    
    public BookService(){
        
    }
    
    @PostConstruct
    public void init(){
        // Construct a JAX-RS Client
        jaxRsClient = ClientBuilder.newClient();
        loadData();
    }
    
    private void loadData(){
            
        bookList = jaxRsClient.target(hostUri + "/org.javaee8recipes.bookstore.entity.book")
                .request("application/xml")
                .get(new GenericType<List<Book>>() {
                }
                );
    }
    

    /**
     * @return the bookList
     */
    public List<Book> getBookList() {
        if(bookList == null){
             loadData();
        }
        return bookList;
    }

    /**
     * @param bookList
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
    
    /**
     * Persists a <code>Book</code> object.
     * @param book 
     */
    public void create(Book book){
        bookFacade.create(book);
    }
}
