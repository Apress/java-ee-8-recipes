/*
 * Recipe 13-3:  Allocating A Specific Bean for Injection
 */
package org.javaee8recipes.chapter13.recipe13_03;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author juneau
 */
@Named(value = "paperbackController")
@SessionScoped
@Paperback
public class PaperbackController implements Serializable, Book {

    /**
     * Creates a new instance of PaperbackController
     */
    public PaperbackController() {
    }
}
