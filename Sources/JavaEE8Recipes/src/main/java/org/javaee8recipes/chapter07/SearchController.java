package org.javaee8recipes.chapter07;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.inject.Inject;
import org.javaee8recipes.chapter07.dao.AuthorDAO;

/**
 * Chapter 7
 *
 * @author juneau
 */
@RequestScoped
@ManagedBean(name = "ch7SearchController")
public class SearchController implements java.io.Serializable {

    private String searchText;
    private String errorText;
    @ManagedProperty(value = "#{ch7AuthorController}")
    private AuthorController authorController;
    AuthorDAO authorDAO = new AuthorDAO();

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
        List<Author> tempList = authorDAO.obtainCompleteAuthorList();
        boolean found = false;
        for (Author author : tempList) {
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
