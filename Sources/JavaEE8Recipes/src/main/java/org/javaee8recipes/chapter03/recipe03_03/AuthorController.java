
package org.javaee8recipes.chapter03.recipe03_03;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Recipe 3-3
 * @author juneau
 */
@Named(value = "authorController")
@SessionScoped
public class AuthorController implements Serializable {

    private String newAuthorFirst;
    private String newAuthorLast;
    private String bio;
    private List <Author> authorList;
    
    /**
     * Creates a new instance of RecipeController
     */
    public AuthorController() {
        populateAuthorList();
    }
    
    private void populateAuthorList(){
        System.out.println("initializng authors");
        authorList = new ArrayList<>();
        authorList.add(new Author("Josh", "Juneau", null));
        authorList.add(new Author("Carl", "Dea", null));
        authorList.add(new Author("Mark", "Beaty", null));
        authorList.add(new Author("John", "O'Conner", null));
        authorList.add(new Author("Freddy", "Guime", null));
        System.out.println("AuthorList size:" +authorList.size());
        
        
    }
    
    public void addAuthor() {
        getAuthorList().add(
                new Author(this.getNewAuthorFirst(),
                           this.getNewAuthorLast(),
                           this.getBio()));
    }

    /**
     * @return the authorList
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * @param authorList the authorList to set
     */
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    /**
     * @return the newAuthorFirst
     */
    public String getNewAuthorFirst() {
        return newAuthorFirst;
    }

    /**
     * @param newAuthorFirst the newAuthorFirst to set
     */
    public void setNewAuthorFirst(String newAuthorFirst) {
        this.newAuthorFirst = newAuthorFirst;
    }

    /**
     * @return the newAuthorLast
     */
    public String getNewAuthorLast() {
        return newAuthorLast;
    }

    /**
     * @param newAuthorLast the newAuthorLast to set
     */
    public void setNewAuthorLast(String newAuthorLast) {
        this.newAuthorLast = newAuthorLast;
    }

    /**
     * @return the bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio the bio to set
     */
    public void setBio(String bio) {
        this.bio = bio;
    }
}
