/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Juneau
 */
public class BookTitleValidator implements ConstraintValidator<JavaBookTitle, String> {
    
    @Override
    public void initialize(JavaBookTitle constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(String title, ConstraintValidatorContext cvc) {
        if(title.toUpperCase().contains("JAVA")){
            return true;
        } else {
            return false;
        }
    }
}
