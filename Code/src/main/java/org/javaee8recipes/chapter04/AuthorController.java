package org.javaee8recipes.chapter04;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Chapter 4
 *
 * @author juneau
 */
@SessionScoped
@Named(value = "ch4AuthorController")
public class AuthorController implements Serializable {

    private List<Author> authorBookList = null;
    private List<Author> authorList = null;
    private List<Author> completeAuthorList = null;
    private String storeName = "Acme Bookstore";

    private final String juneauBio =
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
    private final String deaBio = "This is Carl Dea's Bio";
    private final String beatyBio = "This is Mark Beaty's Bio";
    private final String oConnerBio = "This is John O'Connor's Bio";
    private final String guimeBio = "This is Freddy Guime's Bio";
    private Author current;
    private String authorLast;
    
    private Book currentBook;

    /**
     * Creates a new instance of RecipeController
     */
    public AuthorController() {
        
    }
    
    @PostConstruct
    public void init(){
        authorLast = null;
        populateAuthors();
        populateJavaRecipesAuthorList();
    }
    
    private void populateAuthors(){
        authorBookList = null;
        Book book1 = new Book("Java 7 Recipes", "java7recipes.png");
        
        // Chapters used for Recipe 4-10
        book1 = addChapters1(book1);
        
        
        Book book2 = new Book("Java EE 7 Recipes", "javaee7recipes.png");
        book2 = addChapters2(book2);
        Book book3 = new Book("Java FX 2.0: Introduction By Example", "javafx.png");
        authorBookList = new ArrayList<>();
        
        Author author1 = new Author("Josh", "Juneau", juneauBio);
        author1.addBook(book1);
        author1.addBook(book2);
        authorBookList.add(author1);
        
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
     * Utility method for adding chapters...not very clean.  This will be
     * replaced with database tables/logic in future chapters
     * @param book
     * @return 
     */
    private Book addChapters1(Book book){
        Chapter chapter1 = new Chapter(1, "Getting Started with Java 7", null);
        Chapter chapter2 = new Chapter(2, "Strings", null);
        Chapter chapter3 = new Chapter(3, "Numbers and Dates", null);
        Chapter chapter4 = new Chapter(4, "Data Structures, Conditionals, and Iteration", null);
        Chapter chapter5 = new Chapter(5, "Input and Output", null);
        Chapter chapter6 = new Chapter(6, "Exceptions, Logging, and Debugging", null);
        Chapter chapter7 = new Chapter(7, "Object Oriented Java", null);
        Chapter chapter8 = new Chapter(8, "Concurrency", null);
        Chapter chapter9 = new Chapter(9, "Debugging and Unit Testing", null);
        Chapter chapter10 = new Chapter(10, "Unicode, Internationalization, and Currency Codes", null);
        Chapter chapter11 = new Chapter(11, "Working with Databases (JDBC)", null);
        Chapter chapter12 = new Chapter(12, "Java 2D Graphics and Media", null);
        Chapter chapter13 = new Chapter(13, "Java 3D", null);
        Chapter chapter14 = new Chapter(14, "Swing API", null);
        Chapter chapter15 = new Chapter(15, "JavaFX Fundamentals", null);
        Chapter chapter16 = new Chapter(16, "Graphics with JavaFX", null);
        Chapter chapter17 = new Chapter(17, "Media with JavaFX", null);
        Chapter chapter18 = new Chapter(18, "Working with Servlets", null);
        Chapter chapter19 = new Chapter(19, "Applets", null);
        Chapter chapter20 = new Chapter(20, "JavaFX on the Web", null);
        Chapter chapter21 = new Chapter(21, "Email", null);
        Chapter chapter22 = new Chapter(22, "XML and Web Services", null);
        Chapter chapter23 = new Chapter(23, "Networking", null);
        List <Chapter> chapterList = new ArrayList();
        chapterList.add(chapter1);
        chapterList.add(chapter2);
        chapterList.add(chapter3);
        chapterList.add(chapter4);
        chapterList.add(chapter5);
        chapterList.add(chapter6);
        chapterList.add(chapter7);
        chapterList.add(chapter8);
        chapterList.add(chapter9);
        chapterList.add(chapter10);
        chapterList.add(chapter11);
        chapterList.add(chapter12);
        chapterList.add(chapter13);
        chapterList.add(chapter14);
        chapterList.add(chapter15);
        chapterList.add(chapter16);
        chapterList.add(chapter17);
        chapterList.add(chapter18);
        chapterList.add(chapter19);
        chapterList.add(chapter20);
        chapterList.add(chapter21);
        chapterList.add(chapter22);
        chapterList.add(chapter23);
        book.setChapters(chapterList);
        return book;

    }
    
    /**
     * Utility method for adding chapters...not very clean.  This will be
     * replaced with database tables/logic in future chapters
     * @param book
     * @return 
     */
    private Book addChapters2(Book book){
        Chapter chapter1 = new Chapter(1, "Working with Servlets", null);
        Chapter chapter2 = new Chapter(2, "JavaServer Pages", null);
        Chapter chapter3 = new Chapter(3, "The Basics of JavaServer Faces", null);
        Chapter chapter4 = new Chapter(4, "Facelets", null);
        Chapter chapter5 = new Chapter(5, "JavaServer Faces Components", null);
        Chapter chapter6 = new Chapter(6, "Advanced JSF and AJAX", null);
        Chapter chapter7 = new Chapter(7, "Databases (JDBC)", null);
        Chapter chapter8 = new Chapter(8, "Object Relational Mapping with Java EE", null);
        Chapter chapter9 = new Chapter(9, "Java EE and Enterprise Java Beans", null);
        Chapter chapter10 = new Chapter(10, "EJB Query Language", null);
        Chapter chapter11 = new Chapter(11, "Oracle's Glassfish", null);
        Chapter chapter12 = new Chapter(12, "Context and Dependency Injection", null);
        Chapter chapter13 = new Chapter(13, "Java Message Service", null);
        Chapter chapter14 = new Chapter(14, "Authentication and Security", null);
        Chapter chapter15 = new Chapter(15, "Java Web Services", null);
        Chapter chapter16 = new Chapter(16, "Dynamic Language Web Solutions", null);
        Chapter chapter17 = new Chapter(17, "Deploying Applications to the Cloud", null);
        Chapter chapter18 = new Chapter(18, "JavaFX in the Enterprise", null);
        Chapter chapter19 = new Chapter(19, "Writing JavaFX with a Different Language", null);
      
        List <Chapter> chapterList = new ArrayList();
        chapterList.add(chapter1);
        chapterList.add(chapter2);
        chapterList.add(chapter3);
        chapterList.add(chapter4);
        chapterList.add(chapter5);
        chapterList.add(chapter6);
        chapterList.add(chapter7);
        chapterList.add(chapter8);
        chapterList.add(chapter9);
        chapterList.add(chapter10);
        chapterList.add(chapter11);
        chapterList.add(chapter12);
        chapterList.add(chapter13);
        chapterList.add(chapter14);
        chapterList.add(chapter15);
        chapterList.add(chapter16);
        chapterList.add(chapter17);
        chapterList.add(chapter18);
        chapterList.add(chapter19);
        book.setChapters(chapterList);
        return book;
        
    }
    
    /**
     * Searches through all Author objects and populates the authorList
     * with only those authors who were involved with the Java 7 Recipes book
     * @return 
     */
    private String populateJavaRecipesAuthorList() {
        authorList = new ArrayList();
        for(Author author:authorBookList){
            List<Book>books = author.getBooks();
            for(Book book:books){
                if(book.getTitle().equals("Java 7 Recipes")){
                    // Set the currently selected book
                    setCurrentBook(book);
                    authorList.add(author);
                }
            }
        }
        
        return "book";
    }

    /**
     * Searches through all Author objects and populates the authorList
     * with only those authors who were involved with the Java EE 7 Recipes book
     * @return 
     */
    private String populateJavaEERecipesAuthorList() {
        authorList = new ArrayList();
        for(Author author:authorBookList){
            List<Book>books = author.getBooks();
            for(Book book:books){
                if(book.getTitle().equals("Java EE 7 Recipes")){
                    // Set the currently selected book
                    setCurrentBook(book);
                    authorList.add(author);
                }
            }
        }
        return "book";

    }
    
    /**
     * Populates completeAuthorList with each existing Author object
     * @return 
     */
    private String populateCompleteAuthorList() {
        completeAuthorList = new ArrayList();
        for(Author author:authorBookList){
            completeAuthorList.add(author);
        }
        currentBook = null;
        return "book";
    }

    public String displayAuthor(String last) {
        for (Author author : authorList) {
            if (author.getLast().equals(last)) {
                current = author;
            }
        }
        return "bio";
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

    /**
     * @return the currentBook
     */
    public Book getCurrentBook() {
        return currentBook;
    }

    /**
     * @param currentBook the currentBook to set
     */
    public void setCurrentBook(Book currentBook) {
        this.currentBook = currentBook;
    }
}
