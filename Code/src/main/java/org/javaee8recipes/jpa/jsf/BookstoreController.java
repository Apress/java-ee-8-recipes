
package org.javaee8recipes.jpa.jsf;

import java.util.Date;
import javax.faces.bean.ApplicationScoped;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author juneau
 */
@ManagedBean(name = "bookstoreController")
@ApplicationScoped
public class BookstoreController {

    private Date dayAndTime = null;

    /**
     * Creates a new instance of BookstoreController
     */
    public BookstoreController() {
    }

    /**
     * @return the dayAndTime
     */
    public Date getDayAndTime() {
        dayAndTime = new Date();
        return dayAndTime;
    }

    /**
     * @param dayAndTime the dayAndTime to set
     */
    public void setDayAndTime(Date dayAndTime) {
        this.dayAndTime = dayAndTime;
    }
}