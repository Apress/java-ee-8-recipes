
package org.javaee8recipes.chapter05.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.javaee8recipes.chapter05.annotation.PasswordLength;

/**
 * Custom validation class to ensure password is long enough
 * @author juneau
 */
public class CheckPasswordValidator implements
        ConstraintValidator<PasswordLength, Object> {
    private int passwordLength;

    private String password;
    @Override
    public void initialize(PasswordLength constraintAnnotation) {
        // Initilize implementation here
        passwordLength = constraintAnnotation.passwordLength();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean returnValue = false;
        if (value.toString().length() >= passwordLength){
            returnValue = true;
        } else {
            returnValue = false;
        }
        return returnValue;
    }
    
}
