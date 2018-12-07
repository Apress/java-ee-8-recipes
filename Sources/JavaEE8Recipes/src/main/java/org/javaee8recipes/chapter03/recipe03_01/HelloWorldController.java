
package org.javaee8recipes.chapter03.recipe03_01;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * Recipe 3-1:  A Simple JSF Application
 * @author juneau
 */
@Named(value = "helloWorldController")
@RequestScoped
public class HelloWorldController implements Serializable {
    
    private String hello;

    /**
     * Creates a new instance of HelloWorldBean
     */
    public HelloWorldController() {
       
    }
    
    @PostConstruct
    public void init(){
        System.out.println ("Instantiated helloWorldController");
        hello = "Hello World";
    }

    /**
     * @return the hello
     */
    public String getHello() {
        return hello;
    }

    /**
     * @param hello the hello to set
     */
    public void setHello(String hello) {
        this.hello = hello;
    }
}
