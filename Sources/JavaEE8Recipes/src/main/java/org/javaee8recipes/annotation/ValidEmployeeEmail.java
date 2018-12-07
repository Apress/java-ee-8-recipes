package org.javaee8recipes.annotation;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy={ValidEmployeeEmailValidator.class})
public @interface ValidEmployeeEmail {

    String message() default "{org.javaee8recipes.annotation.ValidEmployeeEmail.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    
}
