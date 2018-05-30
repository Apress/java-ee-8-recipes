
package org.javaee8recipes.jpa.jsf;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.javaee8recipes.jpa.session.BookstoreSessionCounter;

/**
 *
 * @author juneau
 */
@ManagedBean(name="bookstoreSessionController")
@SessionScoped
public class BookstoreSessionController {
    
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
