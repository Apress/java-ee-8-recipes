
package org.javaee8recipes.jpa.timer;

import javax.ejb.Singleton;
import javax.ejb.Schedule;

/**
 * Recipe 9-9 : The EJB Timer Service
 * @author juneau
 */
@Singleton
public class TimerBean {
        
    /**
     * Automatic Timer Example
     *
     */
    @Schedule(minute="*/5", hour="*")
    public void automaticTimer(){
        System.out.println("*** Automatic Timeout Occurred ***");
    }
}
