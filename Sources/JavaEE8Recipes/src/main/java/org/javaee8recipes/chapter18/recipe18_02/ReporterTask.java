package org.javaee8recipes.chapter18.recipe18_02;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import org.javaee8recipes.entity.Book;
import org.javaee8recipes.entity.BookAuthor;
import org.javaee8recipes.chapter09.session.BookAuthorFacade;
import org.javaee8recipes.chapter09.session.BookFacade;

/**
 * Example of a Reporter Task
 *
 * @author Juneau
 */
public class ReporterTask implements Runnable {
    
    @Resource
    UserTransaction ut;

    String reportName;
    @EJB
    private BookAuthorFacade bookAuthorFacade;
    @EJB
    private BookFacade bookFacade;

    public ReporterTask(String reportName) {
        this.reportName = reportName;
    }

    public void run() {
// Run the named report
        if ("AuthorReport".equals(reportName)) {
            runAuthorReport();


        } else if ("BookReport".equals(reportName)) {
            System.out.println("running book report...");
            runBookReport();
        }
    }

    /**
     * Prints a list of authors to the system log.
     */
    public void runAuthorReport() {
        List<BookAuthor> authors = bookAuthorFacade.findAuthor();
        System.out.println("Author Listing Report");
        System.out.println("=====================");

        for (BookAuthor author : authors) {
            System.out.println(author.getFirst() + " " + author.getLast());
        }
    }

    /**
     * Prints a list of books
     */
    void runBookReport() {
        
        System.out.println("Querying the database");
        Path reportFile = Paths.get("BookReport.txt");
       
        try (BufferedWriter writer = Files.newBufferedWriter(
                reportFile, Charset.defaultCharset())) {
            Files.deleteIfExists(reportFile);
            reportFile = Files.createFile(reportFile);
            writer.append("Book Listing Report");
            writer.newLine();
            writer.append("===================");
            writer.newLine();
            List<Book> books = bookFacade.findAllBooks();
            for (Book book : books) {
                writer.append(book.getTitle());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException exception) {
            System.out.println("Error writing to file");
        }


    }
}
