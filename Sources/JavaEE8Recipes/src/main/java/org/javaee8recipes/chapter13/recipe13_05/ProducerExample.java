
package org.javaee8recipes.chapter13.recipe13_05;


import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 13-5:  Producer fields and methods
 * @author juneau
 */
@Named
@SessionScoped
public class ProducerExample implements java.io.Serializable {
    
    @Inject
    @InitValue
    private int initial;
    
    private int orderList = -1;
    
    public ProducerExample(){
        
    }
    
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
        if (orderList == -1)
            orderList = initial;
        return orderList;
    }

    /**
     * @param orderList the orderList to set
     */
    public void setOrderList(int orderList) {
        this.orderList = orderList;
    }
    
  
    /**
    @Produces @BookQualifier public Book getBook(Book book){
        
        if(book.equals(Ebook.class))
            return new EbookController();
        else 
            return new PaperbackController();
    }
    * */
    
}
