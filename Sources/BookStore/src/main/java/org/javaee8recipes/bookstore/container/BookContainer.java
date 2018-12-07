/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.bookstore.container;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.javaee8recipes.bookstore.entity.Book;

/**
 *
 * @author Juneau
 */
@Named
@SessionScoped
public class BookContainer implements java.io.Serializable {
    
    private Book book;
    
    private List<Book> bookList;
    
    public BookContainer(){
        
    }

    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * @return the bookList
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * @param bookList the bookList to set
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
