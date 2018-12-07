
package org.javaee8recipes.chapter09.jsf;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.javaee8recipes.entity.Employee;
import org.javaee8recipes.chapter09.session.EmployeeSession;

/**
 *
 * @author Juneau
 */
@Named(value = "employeeControllerFinal")
@RequestScoped
public class EmployeeController {
    
    @EJB
    EmployeeSession ejbFacade;
    
    private List<Employee> employeeList;

    /**
     * Creates a new instance of EmployeeController
     */
    public EmployeeController() {
    }
    
    @PostConstruct
    public void init(){
        setEmployeeList(ejbFacade.findAll());
    }
    
    
    public List getActiveEmployeeCount(){
        return ejbFacade.obtainActiveEmployeeCount();
    }
    
    public void inactivateEmployees(){
        String message = ejbFacade.updateEmployeeStatusActive();
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                message, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        setEmployeeList(ejbFacade.findAll());
    }

    public void activateEmployees(){
        String message = ejbFacade.updateEmployeeStatusInactive();
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                message, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        setEmployeeList(ejbFacade.findAll());
    }
    
    /**
     * @return the employeeList
     */
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    /**
     * @param employeeList the employeeList to set
     */
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    
}
