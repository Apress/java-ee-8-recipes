
package org.javaee8recipes.jpa.jsf;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.javaee8recipes.jpa.entity.Employee;
import org.javaee8recipes.jpa.session.EmployeeSession;

/**
 *
 * @author Juneau
 */
@Named(value = "employeeControllerFinal")
@RequestScoped
public class EmployeeController {
    
    @EJB
    EmployeeSession ejbFacade;

    /**
     * Creates a new instance of EmployeeController
     */
    public EmployeeController() {
    }
    
    public List getActiveEmployeeCount(){
        return ejbFacade.obtainActiveEmployeeCount();
    }
    
}
