
package org.javaee8recipes.chapter03.recipe03_13;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * Used for Recipe 3-13, specifying view actions
 * @author Juneau
 */
@Named(value = "viewActionManagedBean")
@SessionScoped
public class ViewActionManagedBean implements Serializable {
    // The following object represents an authenticated user 
    Visitor visitor = new Visitor();
    /**
     * Creates a new instance of ViewActionManagedBean
     */
    public ViewActionManagedBean() {
    }
    
    public String validateUser() {
        String viewName;
        System.out.println("Look in the server log to see this message");
        // Here we would perform validation based upon the user visiting the
        // site to ensure that they had the appropriate permissions to view
        // the selected view.  For the purposes of this example, this
        // conditional logic is just a prototype.
        if (visitor.isAdmin()){
            // visit the current page
            viewName = null;
            System.out.println("Current User is an Admin");
        } else {
            viewName = "notAdmin";
            System.out.println("Current User is NOT an Admin");
        }
        return viewName;
    }
}
