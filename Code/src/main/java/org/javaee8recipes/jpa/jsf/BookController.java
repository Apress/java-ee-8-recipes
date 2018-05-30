
package org.javaee8recipes.jpa.jsf;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.javaee8recipes.jpa.entity.Book;
import org.javaee8recipes.jpa.entity.BookAuthor;
import org.javaee8recipes.jpa.session.BookFacade;

/**
 *
 * @author juneau
 */
@ManagedBean(name="bookController")
@SessionScoped
public class BookController implements java.io.Serializable {
    
    @EJB
    BookFacade ejbFacade;
    
    private List<Book> completeBookList = null;
    private List<Map> customBookList = null;
    private List<Book> booksByAuthor = null;
    private List<Book> nativeBookList = null;
    private List<Book> namedNativeBookList = null;

    
    @ManagedProperty(value = "#{authorConroller}")
    private AuthorController authorController;
    
    public BookController(){
        
    }

    /**
     * @return the completeBookList
     */
    public List<Book> getCompleteBookList() {
        completeBookList = ejbFacade.findAll();
        return completeBookList;
    }
    
    /**
     * 
     */
    public List<Map> getCustomBookList(){
        customBookList = ejbFacade.obtainCustomList();
        return customBookList;
    }

    /**
     * @param completeBookList the completeBookList to set
     */
    public void setCompleteBookList(List<Book> completeBookList) {
        this.completeBookList = completeBookList;
    }
    
    public String populateBookList(BigDecimal bookId){
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
    
    /**
     * Recipe 10-2
     * @param author
     * @return 
     */
    public String findBooksByAuthor(BookAuthor author){
        setBooksByAuthor(ejbFacade.findBooksByAuthor(author));
        return "/chapter10/recipe10_2b.xhtml";
    }

    /**
     * @return the booksByAuthor
     */
    public List<Book> getBooksByAuthor() {
        return booksByAuthor;
    }

    /**
     * @param booksByAuthor the booksByAuthor to set
     */
    public void setBooksByAuthor(List<Book> booksByAuthor) {
        this.booksByAuthor = booksByAuthor;
    }
    
    /**
     * Recipe 10-3a: Using a Native Query
     */

    public List<Book> getNativeBookList() {
        nativeBookList = ejbFacade.obtainNativeList();
        return nativeBookList;
    }

    /**
     * @param nativeBookList the nativeBookList to set
     */
    public void setNativeBookList(List<Book> nativeBookList) {
        this.nativeBookList = nativeBookList;
    }

    /**
     * @return the namedNativeBookList
     */
    public List<Book> getNamedNativeBookList() {
        namedNativeBookList = ejbFacade.obtainNamedNativeList();
        return namedNativeBookList;
    }

    /**
     * @param namedNativeBookList the namedNativeBookList to set
     */
    public void setNamedNativeBookList(List<Book> namedNativeBookList) {
        this.namedNativeBookList = namedNativeBookList;
    }
}
