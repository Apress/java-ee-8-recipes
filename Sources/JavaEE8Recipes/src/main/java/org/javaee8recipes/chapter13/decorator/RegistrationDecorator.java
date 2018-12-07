/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter13.decorator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import org.javaee8recipes.chapter13.recipe13_13.BookstoreRegistration;
import org.javaee8recipes.chapter13.recipe13_13.Registration;

/**
 *
 * @author Juneau
 */
@Decorator
public abstract class RegistrationDecorator implements BookstoreRegistration {
    
    @Inject
    @Delegate
    @Any
    private BookstoreRegistration registration;
    
    @Override
    public String register(Registration registration){
        // Submit to registration database table
        // Submit to promotional database table
        return registration.getEmail() + " has been entered into the giveaway";
    }
}
