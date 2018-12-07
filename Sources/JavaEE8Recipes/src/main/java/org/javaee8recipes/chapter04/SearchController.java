
package org.javaee8recipes.chapter04;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 * Chapter 4
 * @author juneau
 */

@RequestScoped
@Named(value = "ch4SearchController")
public class SearchController implements java.io.Serializable {

    private String searchText;
    private String errorText;
    
    @Inject
    private AuthorController authorController;
    
    /**
     * Creates a new instance of SearchController
     */
    public SearchController() {
        searchText = null;
        errorText = null;
    }
    
    public String searchAuthors(List authorList){
        String fullName = null;
        String returnString = null;
        List <Author> tempList = authorList;
       
        for (Author author: tempList){
            fullName = author.getFirst() + " " + author.getLast();
            if (author.getFirst().equalsIgnoreCase(searchText)){
                returnString = authorController.displayAuthor(author.getLast());
            } else if (author.getLast().equalsIgnoreCase(searchText)){
                returnString = authorController.displayAuthor(author.getLast());
            } else if (fullName.equalsIgnoreCase(searchText)){
                returnString = authorController.displayAuthor(author.getLast());
            } 
        }  
        if(returnString == null){
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
