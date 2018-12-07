/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter09.jsf;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.javaee8recipes.chapter09.session.BookCategoryFacade;

/**
 *
 * @author Juneau
 */
@Named(value = "bookCategoryController")
@RequestScoped
public class BookCategoryController {
    
    @EJB
    BookCategoryFacade ejbFacade;

    /**
     * Creates a new instance of BookCategoryController
     */
    public BookCategoryController() {
    }
    
    public List getBookCategories(){
        return ejbFacade.getBookCategories();
    }
}
