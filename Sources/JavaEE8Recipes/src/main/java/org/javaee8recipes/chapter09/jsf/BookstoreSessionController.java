
package org.javaee8recipes.chapter09.jsf;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.javaee8recipes.chapter09.session.BookstoreSessionCounter;

/**
 *
 * @author juneau
 */
@Named("bookstoreSessionController")
@SessionScoped
public class BookstoreSessionController implements java.io.Serializable {
    
    @EJB
    BookstoreSessionCounter bookstoreSessionCounter;
    
    private boolean flag = false;
    private int counter;

    /**
     * @return the counter
     */
    public int getCounter() {
        if(!flag){
            counter = bookstoreSessionCounter.getNumberOfSessions();
            flag = true;
        }
        return counter;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }
    
    
}
