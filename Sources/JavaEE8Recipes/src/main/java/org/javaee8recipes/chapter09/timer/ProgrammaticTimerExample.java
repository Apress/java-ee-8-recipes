
package org.javaee8recipes.chapter09.timer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Singleton;
import javax.ejb.Timer;
import javax.ejb.Timeout;
import javax.ejb.TimerService;

/**
 *
 * @author juneau
 */
@Singleton
//@Startup
public class ProgrammaticTimerExample {
    
    @Resource
    TimerService timerService;
    
    @PostConstruct
    public void createTimer(){
        System.out.println("Creating Timer...");
        Timer timer = timerService.createTimer(100000, "Timer has been created...");
    }
    
    @Timeout
    public void timeout(Timer timer){
        System.out.println("Timeout of Programmatic Timer Example...");
    }
    
}
