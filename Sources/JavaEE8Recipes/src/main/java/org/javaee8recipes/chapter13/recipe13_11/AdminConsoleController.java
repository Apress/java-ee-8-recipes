/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter13.recipe13_11;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.javaee8recipes.chapter13.interceptor.Notified;

/**
 *
 * @author Juneau
 */
@Notified
@Named
@RequestScoped
public class AdminConsoleController {
    
    public AdminConsoleController(){
        
    }
    
    public void login(){
        System.out.println("This is an action method which would allow one to log into an"
                + "administrative console");
    }
    
}
