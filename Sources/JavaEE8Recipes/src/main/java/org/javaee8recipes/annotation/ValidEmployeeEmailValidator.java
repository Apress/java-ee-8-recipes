/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

/**
 *
 * @author Juneau
 */
@SupportedValidationTarget(value = ValidationTarget.ANNOTATED_ELEMENT)
public class ValidEmployeeEmailValidator implements ConstraintValidator<ValidEmployeeEmail, Object[]> {
    @Override
    public void initialize(final ValidEmployeeEmail constraintAnnotation) {
        // no-op
    }
 
    @Override
    public boolean isValid(final Object[] parameters, final ConstraintValidatorContext context) {
        // Ensure employee email is from our organization
        return parameters == null || parameters[0].toString().contains("@acme.com");
    }
}
    
