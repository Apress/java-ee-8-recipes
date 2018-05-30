
package org.javaee8recipes.jpa.jsf;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.javaee8recipes.jpa.entity.BookAuthor;
import org.javaee8recipes.jpa.session.BookAuthorFacade;

/**
 *
 * @author juneau
 */
@ManagedBean(name = "searchController")
@RequestScoped
public class SearchController {

    private String searchText;
    private String errorText;
    @ManagedProperty(value = "#{authorConroller}")
    private AuthorController authorController;

    @EJB
    private BookAuthorFacade bookAuthorFacade;

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
