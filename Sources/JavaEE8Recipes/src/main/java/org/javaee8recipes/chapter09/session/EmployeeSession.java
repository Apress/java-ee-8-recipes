package org.javaee8recipes.chapter09.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import org.javaee8recipes.entity.Employee;

/**
 *
 * @author Juneau
 */
@Stateless
public class EmployeeSession extends AbstractFacade<Employee> {

    @PersistenceContext(unitName = "JavaEERecipesPU")
    private EntityManager em;

    private List employeeList;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeSession() {
        super(Employee.class);
    }

    /**
     * Returns a list of employees that make greater than 50,000
     *
     * @return
     */
    public List obtainActiveEmployeeCount() {
       TypedQuery<Object[]> qry = em.createQuery("SELECT j.title, count(e) "
                + "FROM Jobs j LEFT JOIN j.employees e "
                + "ON e.status = 'ACTIVE' "
                + "WHERE j.salary >= 50000 "
                + "GROUP BY j.title", Object[].class);
        
       List data = new ArrayList();
        if (!qry.getResultList().isEmpty()) {
             List<Object[]> tdata = qry.getResultList();
            for (Object[] t : tdata) {
                HashMap resultMap = new HashMap();
                resultMap.put("title", t[0]);
                resultMap.put("count", t[1]);
                data.add(resultMap);
            }
        }
        return data;

    }
    
    public void createEmp(String firstName, String lastName, String status){
        StoredProcedureQuery qry = em.createStoredProcedureQuery("createEmp")
                .setParameter("FIRSTNAME", firstName)
                .setParameter("LASTNAME",lastName)
                .setParameter("STATUS", status);
        
        try { 
            qry.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public String updateEmployeeStatusInactive() {
        String returnMessage = null;
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate<Employee> q = builder.createCriteriaUpdate(Employee.class);
        Root<Employee> e = q.from(Employee.class);
        q.set(e.get("status"), true)
                .where(builder.equal(e.get("status"), false));
        Query criteriaUpd = em.createQuery(q);
        int result = criteriaUpd.executeUpdate();
        if (result > 0){
            returnMessage = result + " records updated";
        } else {
            returnMessage = "No records updated";
        }
        return returnMessage;
    }
    
    public String updateEmployeeStatusActive() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate<Employee> q = builder.createCriteriaUpdate(Employee.class);
        Root<Employee> e = q.from(Employee.class);
        q.set(e.get("status"), false)
                .where(builder.equal(e.get("status"), true));
        Query criteriaUpd = em.createQuery(q);
        criteriaUpd.executeUpdate();
        return "Successfully updated records to INACTIVE";
    }
    
    public String obtainEmployees(){
        Query qry = em.createQuery("select e from Employee e");
        employeeList = qry.getResultList();
        return null;
    }

    public String deleteEmployeeOnStatus(String condition) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete<Employee> q = builder.createCriteriaDelete(Employee.class);
        Root<Employee> e = q.from(Employee.class);
        q.where(builder.equal(e.get("status"), condition));
        
        System.out.println("here in the update method");
        return null;
    }
    
    /**
     * @return the employeeList
     */
    public List getEmployeeList() {
        if(employeeList == null){
            obtainEmployees();
        }
        return employeeList;
    }

    /**
     * @param employeeList the employeeList to set
     */
    public void setEmployeeList(List employeeList) {
        this.employeeList = employeeList;
    }
    

}
