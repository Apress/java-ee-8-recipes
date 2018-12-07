/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter17.recipe17_02;


import java.io.Serializable;
import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author juneau
 */
// Commented out for application-loading purposes.  Since this bean contains the
// same @Name as the Recipe17_02 class, we cannot load it at the same time.  Uncomment
// for testing purposes.
//@Named("recipe17_02")
@SessionScoped
@DeclareRoles("users")
public class Recipe17_02_1 implements Serializable {

    /**
     * Creates a new instance of Recipe17_02
     */
    public Recipe17_02_1() {
    }
    
    public String nextPage(){
        return "recipe17_02b.xhtml";
    }
}
