package org.javaee8recipes.chapter07;

import org.javaee8recipes.chapter07.dao.AuthorDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.javaee8recipes.chapter07.dao.AuthorWorkDAO;
import org.javaee8recipes.chapter07.dao.BookDAO;

/**
 * Chapter7
 *
 * @author juneau
 */
@SessionScoped
@Named(value = "ch7AuthorController")
public class AuthorController implements Serializable {

    private AuthorDAO authorDao = null;
    private BookDAO bookDao = null;
    private AuthorWorkDAO authorWork = null;
    private List<Author> authorBookList = null;
    private List<Author> authorList = null;
    private List<Author> completeAuthorList = null;
    private String storeName = "Acme Bookstore";
    private Author current;
    private String authorLast;
    private Book currentBook;

    /**
     * Creates a new instance of RecipeController
     */
    public AuthorController() {
        authorLast = null;
        authorDao = new AuthorDAO();
        bookDao = new BookDAO();
        authorWork = new AuthorWorkDAO();
        // Query database for all authors
        authorBookList = authorDao.obtainCompleteAuthorList();
    }

    /**
     * Searches through all Author objects and populates the authorList with
     * only those authors who were involved with the specified book
     *
     * @return
     */
    public String populateAuthorList(int bookId) {
        authorList = new ArrayList();
        if (authorBookList == null) {
            authorBookList = authorDao.obtainCompleteAuthorList();
        }

        List<AuthorWork> awList = authorWork.performFind(bookId);
        for (AuthorWork work : awList) {

            Author foundAuthor = authorDao.performFind(work.getAuthorId());
            // Set the currently selected book

            authorList.add(foundAuthor);
        }

        setCurrentBook(bookDao.performFind(bookId));
        return "/chapter07/book";
    }

    /**
     * Populates completeAuthorList with each existing Author object
     *
     * @return
     */
    private String populateCompleteAuthorList() {
        completeAuthorList = new ArrayList();
        for (Author author : authorBookList) {
            completeAuthorList.add(author);
        }
        currentBook = null;
        return "/chapter07/book";
    }

    public String displayAuthor(String last) {
        if(authorList == null){
            authorList = authorDao.obtainCompleteAuthorList();
        }
        List<AuthorWork> awList = null;
        
        boolean foundFlag = false;
        for (Author author : authorList) {

            if (author.getLast().equals(last)) {
                // Find author's books and populate book list
                awList = authorWork.performFindBooks(author.getId());
                List <Book> authorBooks = new ArrayList();
                for(AuthorWork authWork:awList){
                    Book newbook = bookDao.performFind(authWork.getBookId());

                    authorBooks.add(newbook);
                }
                author.setBooks(authorBooks);
                setCurrent(author);
            }
        }
        System.out.println("Current author:" + current.getBooks().size());
        return "/chapter07/bio";
    }

    /**
     * @return the authorList
     */
    public List getAuthorList() {
        return authorList;
    }

    /**
     * @return the current
     */
    public Author getCurrent() {
        System.out.println("Current author is:  "+ current.getFirst());
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

    public void findAuthor() {
        if (this.authorLast != null) {
            for (Author author : authorList) {
                if (author.getLast().equals(authorLast)) {
                    this.current = author;
                }
            }
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null,
                    new FacesMessage("No last name specified."));

        }
    }

    /**
     * Auto-completes author names from the authorBookList
     *
     * @param text
     * @return
     */
    public List<String> complete(String text) {
        List<String> results = new ArrayList();
        // This should print each time you type a letter in the autocomplete box
        System.out.println("completing: " + text);
        for (Author author : authorBookList) {
            if (author.getLast().toUpperCase().contains(text.toUpperCase())) {
                results.add(author.getLast().toUpperCase() + " " + author.getFirst().toUpperCase());
            }
        }

        return results;
    }
}
