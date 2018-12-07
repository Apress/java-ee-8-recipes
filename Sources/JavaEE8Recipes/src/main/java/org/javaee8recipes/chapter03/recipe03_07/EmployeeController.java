
package org.javaee8recipes.chapter03.recipe03_07;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Recipe 3-7
 * @author juneau
 */
@Named("employeeController")
@SessionScoped
public class EmployeeController implements Serializable {
    
  
    private String employeeFirst;
    private String employeeLast;
    private String employeeTitle;
    
    private List <Employee> employeeList;
    
    public EmployeeController(){
        employeeFirst = null;
        employeeLast = null;
        employeeTitle = null;
        employeeList = new ArrayList();
    }
    
    public void insertEmployee(){
        Employee emp = new Employee(employeeFirst,
                                    employeeLast,
                                    employeeTitle);
        employeeList.add(emp);
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Successfully Added", null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    /**
     * @return the employeeFirst
     */
    public String getEmployeeFirst() {
        return employeeFirst;
    }

    /**
     * @param employeeFirst the employeeFirst to set
     */
    public void setEmployeeFirst(String employeeFirst) {
        this.employeeFirst = employeeFirst;
    }

    /**
     * @return the employeeLast
     */
    public String getEmployeeLast() {
        return employeeLast;
    }

    /**
     * @param employeeLast the employeeLast to set
     */
    public void setEmployeeLast(String employeeLast) {
        this.employeeLast = employeeLast;
    }

    /**
     * @return the employeeTitle
     */
    public String getEmployeeTitle() {
        return employeeTitle;
    }

    /**
     * @param employeeTitle the employeeTitle to set
     */
    public void setEmployeeTitle(String employeeTitle) {
        this.employeeTitle = employeeTitle;
    }

    /**
     * @return the employeeList
     */
    public List <Employee> getEmployeeList() {
        return employeeList;
    }

    /**
     * @param employeeList the employeeList to set
     */
    public void setEmployeeList(List <Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
