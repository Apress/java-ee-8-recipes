
package org.javaee8recipes.chapter06.recipe06_08;

import javax.faces.application.Application;
import javax.faces.event.*;

/**
 * Recipe 6-8: System Event Listener
 * @author juneau
 */
public class BookstoreAppListener implements SystemEventListener {

    @Override
    public void processEvent(SystemEvent event) throws AbortProcessingException {
        if(event instanceof PostConstructApplicationEvent){
            System.out.println("The application has been constructed...");
        }
        
        if(event instanceof PreDestroyApplicationEvent){
            System.out.println("The application is being destroyed...");
        }
    }

    @Override
    public boolean isListenerForSource(Object source) {
        return(source instanceof Application);
    }
    
}
