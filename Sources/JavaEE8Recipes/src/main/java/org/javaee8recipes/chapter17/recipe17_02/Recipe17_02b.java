/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter17.recipe17_02;

import java.io.Serializable;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author juneau
 */
@Named("recipe17_02b")
@SessionScoped

public class Recipe17_02b implements Serializable {

    /**
     * Creates a new instance of Recipe17_02
     */
    public Recipe17_02b() {
    }
    
    public String unsecuredProcess(){
        return "recipe17_02_1.xhtml";
    }
    
    @RolesAllowed("users")
    public String securedProcess(){
        return "recipe17_02_2.xhtml";
    }
}
