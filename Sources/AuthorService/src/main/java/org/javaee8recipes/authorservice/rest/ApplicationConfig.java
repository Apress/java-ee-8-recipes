/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.authorservice.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Juneau
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(org.javaee8recipes.authorservice.rest.BookAuthorFacadeREST.class);
        //addRestResourceClasses(resources);
        return resources;
    }
}
