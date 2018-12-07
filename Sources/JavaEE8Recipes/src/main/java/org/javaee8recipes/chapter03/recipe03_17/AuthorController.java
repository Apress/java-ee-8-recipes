package org.javaee8recipes.chapter03.recipe03_17;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 * Recipe 3-17
 *
 * @author juneau
 */
@Named(value = "uiRepeatAuthorController")
@SessionScoped
public class AuthorController implements Serializable {

    private List<Author> authorBookList;
    private List<Author> authorList;
    private List<Author> completeAuthorList;
    private String storeName = "Acme Bookstore";

    private String juneauBio =
            "Josh Juneau has been developing software"
            + " since the mid-1990s. PL/SQL development and database programming"
            + " was the focus of his career in the beginning, but as his skills developed,"
            + " he began to use Java and later shifted to it as a primary base for his"
            + " application development. Josh has worked with Java in the form of graphical"
            + " user interface, web, and command-line programming for several years. "
            + "During his tenure as a Java developer, he has worked with many frameworks"
            + " such as JSF, EJB, and JBoss Seam. At the same time, Josh has extended his"
            + " knowledge of the Java Virtual Machine (JVM) by learning and developing applications"
            + " with other JVM languages such as Jython and Groovy. His interest in learning"
            + " new languages that run on the JVM led to his interest in Jython. Since 2006,"
            + " Josh has been the editor and publisher for the Jython Monthly newsletter. "
            + "In late 2008, he began a podcast dedicated to the Jython programming language.";
    private String deaBio = "This is Carl Dea's Bio";
    private String beatyBio = "This is Mark Beaty's Bio";
    private String oConnerBio = "This is John O'Connor's Bio";
    private String guimeBio = "This is Freddy Guime's Bio";
    private Author current;
    private String authorLast;

    /**
     * Creates a new instance of RecipeController
     */
    public AuthorController() {
        
    }
    
    @PostConstruct
    public void init(){
        populateAuthors();
        populateJavaRecipesAuthorList();
        populateCompleteAuthorList();
    }
    private void populateAuthors(){
        Book book1 = new Book("Java 9 Recipes", "java9recipes.png");
        Book book2 = new Book("Java EE 7 Recipes", "javaee7recipes.png");
        Book book3 = new Book("Java FX 2.0: Introduction By Example", "javafx.png");
        authorBookList = new ArrayList<Author>();
        
        Author author1 = new Author("Josh", "Juneau", juneauBio);
        author1.addBook(book1);
        author1.addBook(book2);
        authorBookList.add(author1);
        current = author1;
        
        Author author2 = new Author("Carl", "Dea", deaBio);
        author2.addBook(book1);
        author2.addBook(book3);
        authorBookList.add(author2);
        
        Author author3 = new Author("Mark", "Beaty", beatyBio);
        author3.addBook(book1);
        authorBookList.add(author3);
        
        Author author4 = new Author("John", "O'Conner", oConnerBio);
        author4.addBook(book1);
        authorBookList.add(author4);
        
        Author author5 = new Author("Freddy", "Guime", guimeBio);
        author5.addBook(book1);
        authorBookList.add(author5);
    }

    /**
     * Searches through all Author objects and populates the authorList
     * with only those authors who were involved with the Java 7 Recipes book
     * @return 
     */
    public String populateJavaRecipesAuthorList() {
        authorList = new ArrayList<>();
        for(Author author:authorBookList){
            List<Book>books = author.getBooks();
            for(Book book:books){
                if(book.getTitle().equals("Java 7 Recipes")){
                    authorList.add(author);
                }
            }
        }
        
        return "recipe04_05a";
    }

    /**
     * Searches through all Author objects and populates the authorList
     * with only those authors who were involved with the Java EE 7 Recipes book
     * @return 
     */
    public String populateJavaEERecipesAuthorList() {
        authorList = new ArrayList<>();
        for(Author author:authorBookList){
            List<Book>books = author.getBooks();
            for(Book book:books){
                if(book.getTitle().equals("Java EE 7 Recipes")){
                    authorList.add(author);
                }
            }
        }
        return "recipe04_05b";

    }
    
    /**
     * Populates completeAuthorList with each existing Author object
     * @return 
     */
    private void populateCompleteAuthorList() {
        completeAuthorList = new ArrayList();
        for(Author author:authorBookList){
            completeAuthorList.add(author);
        }
        
        
    }

    public String displayAuthor(String last) {
        for (Author author : authorList) {
            if (author.getLast().equals(last)) {
                current = author;
            }
        }
        return "recipe03_15c";
    }

    /**
     * @return the authorList
     */
    public List getauthorList() {
        return authorList;
    }

    /**
     * @return the current
     */
    public Author getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(Author current) {
        this.current = current;
    }

    /**
     * @return the authorLast
     */
    public String getAuthorLast() {
        return authorLast;
    }

    /**
     * @param authorLast the authorLast to set
     */
    public void setAuthorLast(String authorLast) {
        displayAuthor(authorLast);
    }

    /**
     * @return the storeName
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * @param storeName the storeName to set
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * @return the completeAuthorList
     */
    public List<Author> getCompleteAuthorList() {
        return completeAuthorList;
    }

    /**
     * @param completeAuthorList the completeAuthorList to set
     */
    public void setCompleteAuthorList(List<Author> completeAuthorList) {
        this.completeAuthorList = completeAuthorList;
    }
}
