package org.javaee8recipes.chapter13.recipe13_05;

import java.lang.annotation.*;
import javax.inject.Qualifier;

/**
 * 13-5 Qualifier
 * @author juneau
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Qualifier
public @interface InitValue {} 
