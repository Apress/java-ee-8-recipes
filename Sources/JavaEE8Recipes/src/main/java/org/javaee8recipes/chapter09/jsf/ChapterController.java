/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter09.jsf;


import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import org.javaee8recipes.entity.Book;
import org.javaee8recipes.entity.Chapter;
import org.javaee8recipes.chapter09.session.ChapterFacade;

/**
 *
 * @author juneau
 */
@Named(value = "chapterController")
@SessionScoped
public class ChapterController implements Serializable {
    
    @EJB
    ChapterFacade ejbFacade;
    
    // Uncomment after running wsimport utility and placing the compiled ChapterFacadeService class
    // into the CLASSPATH
   // @WebServiceRef(wsdlLocation="http://localhost:8080/ChapterFacadeService/ChapterFacade?wsdl")
    ChapterFacade chapterService;
    
    private List<Book> booksByChapterTitle;
    private List<Chapter> completeChapterList;
    private List<Book> booksByChapterNumber;

    /**
     * Creates a new instance of ChapterController
     */
    public ChapterController() {
    }
   
    /* Uncomment after running wsimport utiltity and placing the compiled ChapterFacadeService class
     * into the CLASSPATH
     
    public List<Book> loadAllChapters(){
        return chapterService.findAll();
    }
    */
    
    /**
     * @return the booksByChapterTitle
     */
    public List<Book> getBooksByChapterTitle() {
        return booksByChapterTitle;
    }

    /**
     * @param booksByChapterTitle the booksByChapterTitle to set
     */
    public void setBooksByChapterTitle(List<Book> booksByChapterTitle) {
        this.booksByChapterTitle = booksByChapterTitle;
    }
    
    public String populateBooksByChapter(Chapter chapter){
        booksByChapterTitle = ejbFacade.findBookByChapterTitle(chapter);
        return "/chapter10/recipe10_8b.xhtml";
    }

    /**
     * @return the completeChapterList
     */
    public List<Chapter> getCompleteChapterList() {
        completeChapterList = ejbFacade.findAll();
        return completeChapterList;
    }

    /**
     * @param completeChapterList the completeChapterList to set
     */
    public void setCompleteChapterList(List<Chapter> completeChapterList) {
        this.completeChapterList = completeChapterList;
    }

    /**
     * @return the booksByChapterNumber
     */
    public List<Book> getBooksByChapterNumber() {
        return booksByChapterNumber;
    }

    /**
     * @param booksByChapterNumber the booksByChapterNumber to set
     */
    public void setBooksByChapterNumber(List<Book> booksByChapterNumber) {
        this.booksByChapterNumber = booksByChapterNumber;
    }
    
    public String populateBooksByChapterNumber(Chapter chapter){
        booksByChapterNumber = ejbFacade.findAllBooksByChapterNumber(chapter.getChapterNumber());
        return "/chapter10/recipe10_9b.xhtml";
    }
    
    
}
