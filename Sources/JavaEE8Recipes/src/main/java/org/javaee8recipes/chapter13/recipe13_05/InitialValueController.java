
package org.javaee8recipes.chapter13.recipe13_05;

import javax.enterprise.inject.Produces;

/**
 * Recipe 13-5:  Producer Fields and Methods
 * @author juneau
 */

public class InitialValueController implements java.io.Serializable {
    
       @Produces @InitValue public int initialValue = 1000;
       
       
}