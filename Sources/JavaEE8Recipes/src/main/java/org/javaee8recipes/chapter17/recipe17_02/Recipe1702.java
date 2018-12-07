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
@Named("recipe17_02")
@SessionScoped
@DeclareRoles("users")
public class Recipe1702 implements Serializable {

    /**
     * Creates a new instance of Recipe17_02
     */
    public Recipe1702() {
    }
    
    public String nextPage(){
        return "recipe17_02b.xhtml";
    }
}
