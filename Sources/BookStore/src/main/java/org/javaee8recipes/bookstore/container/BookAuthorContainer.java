/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.bookstore.container;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.javaee8recipes.bookstore.entity.BookAuthor;

/**
 *
 * @author Juneau
 */
@Named
@SessionScoped
public class BookAuthorContainer implements java.io.Serializable {

    private BookAuthor bookAuthor;
    
    private List<BookAuthor> bookAuthorList;
    
    public BookAuthorContainer(){
        
    }
    
    /**
     * @return the bookAuthor
     */
    public BookAuthor getBookAuthor() {
        return bookAuthor;
    }

    /**
     * @param bookAuthor the bookAuthor to set
     */
    public void setBookAuthor(BookAuthor bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    /**
     * @return the bookAuthorList
     */
    public List<BookAuthor> getBookAuthorList() {
        return bookAuthorList;
    }

    /**
     * @param bookAuthorList the bookAuthorList to set
     */
    public void setBookAuthorList(List<BookAuthor> bookAuthorList) {
        this.bookAuthorList = bookAuthorList;
    }
    
}
