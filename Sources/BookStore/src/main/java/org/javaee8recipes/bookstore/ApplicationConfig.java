/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.bookstore;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Juneau
 */
@ApplicationPath("bookstore")
public class ApplicationConfig extends Application {

    @Override
    public Map<String, Object> getProperties() {
        final Map<String, Object> map = new HashMap<>();
        //map.put(Csrf.CSRF_PROTECTION, CsrfOptions.EXPLICIT);
        return map;
    }
    
}

