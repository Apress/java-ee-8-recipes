
package org.javaee8recipes.chapter09.session;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

/**
 * Recipe 9-10 - Asynchronous Session Bean
 * @author juneau
 */
@Stateless
public class AsyncBeanExample {
    
    @Asynchronous
    public void asyncMethodCall(){
        // Do Something
        System.out.println("This method is occurring asynchronously...");
    }
    
    
    
}
