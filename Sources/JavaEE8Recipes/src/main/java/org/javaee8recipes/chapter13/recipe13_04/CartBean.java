
package org.javaee8recipes.chapter13.recipe13_04;

// Import and change to @RequestScoped to see a functional difference
//import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author juneau
 */
@Named
@SessionScoped
public class CartBean implements java.io.Serializable {
    
       
    private int orderList = 0;
    
    public CartBean(){}
    
    public void addItem(){
        setOrderList(getOrderList() + 1);
    }
    
    public void removeItem(){
        setOrderList(getOrderList() - 1);
    }

    /**
     * @return the orderList
     */
    public int getOrderList() {
        return orderList;
    }

    /**
     * @param orderList the orderList to set
     */
    public void setOrderList(int orderList) {
        this.orderList = orderList;
    }
    
    
}
