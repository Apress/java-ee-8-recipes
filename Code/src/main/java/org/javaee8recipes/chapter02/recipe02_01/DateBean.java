
package org.javaee8recipes.chapter02.recipe02_01;

import java.util.Date;

/**
 * Recipe 2-1: Creating a Simple JSP
 * @author juneau
 */
public class DateBean implements java.io.Serializable {
    
    private Date currentDate = new Date();

    /**
     * @return the currentDate
     */
    public Date getCurrentDate() {
        return currentDate;
    }

    /**
     * @param currentDate the currentDate to set
     */
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
    
}
