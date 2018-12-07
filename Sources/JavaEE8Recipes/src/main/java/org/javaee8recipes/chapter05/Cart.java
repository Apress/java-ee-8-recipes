
package org.javaee8recipes.chapter05;

import java.util.ArrayList;
import java.util.List;

/**
 * Chapter 5
 * @author juneau
 */
public class Cart implements java.io.Serializable {
    // List containing book objects
    private List<Item> books = null;
    
    public Cart(){
        books = null;
    }

    /**
     * @return the books
     */
    public List <Item> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(List books) {
        this.books = books;
    }
    
    /**
     * Utility method to add a book and quantity
     * @param title
     * @param qty
     */
    public void addBook(Book title, int qty){
        if (books == null){
            books = new ArrayList();
        }
        books.add(new Item(title, qty));
    }
}
