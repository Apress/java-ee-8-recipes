/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter13.recipe13_13;

import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Juneau
 */
@Named
@Dependent
public class BookstoreRegistrationController implements BookstoreRegistration, Serializable {
    
    @Inject
    private Registration current;
    
    public BookstoreRegistrationController(){
        
    }

    /**
     * @return the current
     */
    public Registration getCurrent() {
        if(current == null){
            current = new Registration();
        }
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(Registration current) {
        this.current = current;
    }
    
    public String register(){
        System.out.println("Executing Registration");
        return register(current);
    }

    @Override
    public String register(Registration registration) {
        // Persist current registration
        return null;
    }
    
    
}
