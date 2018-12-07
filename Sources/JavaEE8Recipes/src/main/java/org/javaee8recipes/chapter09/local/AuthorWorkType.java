
package org.javaee8recipes.chapter09.local;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import org.javaee8recipes.entity.AuthorWork;
import org.javaee8recipes.entity.Book;
import org.javaee8recipes.entity.BookAuthor;

/**
 * Local business interface for the AuthorWorkFacade stateless session bean
 * @author juneau
 */
// Comment this annotation to decorate the EJB itself with the annotation
@Local
public interface AuthorWorkType {
    public List<AuthorWork> performFind(BigDecimal bookId);
    
    public List<AuthorWork> performFindByAuthor(BookAuthor authorId);
    
    public Long findAuthorCount(Book book);
}
