
package org.javaee8recipes.chapter03.recipe03_07;

import java.io.Serializable;

/**
 * Recipe 3-7
 * @author juneau
 */
public class Employee implements Serializable {
    private String employeeFirst;
    private String employeeLast;
    private String employeeTitle;

    /**
     * Creates a new instance of EmployeeController
     */
    public Employee() {
        employeeFirst = null;
        employeeLast = null;
        employeeTitle = null;
    }

    public Employee(String first, String last, String title){
        employeeFirst = first;
        employeeLast = last;
        employeeTitle = title;
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
}
