
package org.javaee8recipes.chapter09.jsf;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.javaee8recipes.entity.AuthorWork;
import org.javaee8recipes.entity.Book;
import org.javaee8recipes.chapter09.session.AuthorWorkFacade;

/**
 * JSF Managed bean controller for the AuthorWorkFacade EJB
 * @author juneau
 */
@Named(value = "authorWorkController")
@SessionScoped
public class AuthorWorkController implements Serializable {
    
    @EJB
    AuthorWorkFacade ejbFacade;
    
    private Long authorCount;
    /**
     * Creates a new instance of AuthorWorkController
     */
    public AuthorWorkController() {
    }
    
    /**
     * Invokes the AuthorWorkFacade's performFind method utilizing the
     * business interface.
     * @param book
     * @return 
     */
    public List<AuthorWork> findByBookId(Book book){
        return ejbFacade.performFind(book.getId());
    }
    
    public String obtainAuthorCount(Book book){
        setAuthorCount(ejbFacade.findAuthorCount(book));
        return "/chapter10/recipe10_6b.xhtml";
    }

    /**
     * @return the authorCount
     */
    public Long getAuthorCount() {
        return authorCount;
    }

    /**
     * @param authorCount the authorCount to set
     */
    public void setAuthorCount(Long authorCount) {
        this.authorCount = authorCount;
    }
    
    
}
