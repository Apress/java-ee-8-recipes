
package org.javaee8recipes.chapter09.timer;

import javax.inject.Singleton;
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
