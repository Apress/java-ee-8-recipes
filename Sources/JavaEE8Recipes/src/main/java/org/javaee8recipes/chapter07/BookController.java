
package org.javaee8recipes.chapter07;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;
import org.javaee8recipes.chapter07.dao.BookDAO;

/**
 *
 * @author juneau
 */
@Named(value="ch7BookController")
@SessionScoped
public class BookController implements java.io.Serializable {
    private List<Book> completeBookList = null;
    private BookDAO bookDao = new BookDAO();
    
    @Inject
    private AuthorController authorController;
    
    public BookController(){
        
    }

    /**
     * @return the completeBookList
     */
    public List<Book> getCompleteBookList() {
        completeBookList = bookDao.queryBooks();
        return completeBookList;
    }

    /**
     * @param completeBookList the completeBookList to set
     */
    public void setCompleteBookList(List<Book> completeBookList) {
        this.completeBookList = completeBookList;
    }
    
    public String populateBookList(int bookId){
       String returnValue = getAuthorController().populateAuthorList(bookId);
       return returnValue;
    }

    /**
     * @return the authorController
     */
    public AuthorController getAuthorController() {
        return authorController;
    }

    /**
     * @param authorController the authorController to set
     */
    public void setAuthorController(AuthorController authorController) {
        this.authorController = authorController;
    }
    
}
