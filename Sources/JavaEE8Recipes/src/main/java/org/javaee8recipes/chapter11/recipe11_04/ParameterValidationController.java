/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter11.recipe11_04;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Email;

/**
 *
 * @author Juneau
 */
@Named
@RequestScoped
public class ParameterValidationController {
    
   
    private String email;
    
    
    public void submit(){
        submitEmailAddress(email);
    }
    
    public void submitEmailAddress(@Email String emailAddress){
        System.out.println("Do something with the address: " + emailAddress);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
}
