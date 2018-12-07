
package org.javaee8recipes.chapter09.jsf;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.javaee8recipes.entity.BookAuthor;
import org.javaee8recipes.chapter09.session.BookAuthorFacade;

/**
 *
 * @author juneau
 */
@Named(value = "searchController")
@RequestScoped
public class SearchController {

    @EJB
    private BookAuthorFacade bookAuthorFacade;
    
    @Inject
    private AuthorController authorController;
    
    private String searchText;
    private String errorText;
   
    /**
     * Creates a new instance of SearchController
     */
    public SearchController() {
        searchText = null;
        errorText = null;
    }

    public String searchAuthors() {
        String fullName = null;
        String returnString = null;
        List<BookAuthor> tempList = bookAuthorFacade.findAll();
        boolean found = false;
        for (BookAuthor author : tempList) {
            fullName = author.getLast() + " " + author.getFirst();
            while (!found) {
                if (author.getFirst().equalsIgnoreCase(searchText)) {
                    returnString = getAuthorController().displayAuthor(author.getLast());
                    found = true;
                } else if (author.getLast().equalsIgnoreCase(searchText)) {
                    returnString = getAuthorController().displayAuthor(author.getLast());
                    found = true;
                } else if (fullName.equalsIgnoreCase(searchText)) {
                    returnString = getAuthorController().displayAuthor(author.getLast());
                    found = true;
                }
            }
        }
        if (returnString == null) {
            setErrorText("No Author Found");
        }
        return returnString;
    }

    /**
     * @return the searchText
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * @param searchText the searchText to set
     */
    public void setSearchText(String searchText) {
        this.searchText = searchText;
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
     * @return the errorText
     */
    public String getErrorText() {
        return errorText;
    }

    /**
     * @param errorText the errorText to set
     */
    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
