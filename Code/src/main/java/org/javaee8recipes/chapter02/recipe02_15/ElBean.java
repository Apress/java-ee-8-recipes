
package org.javaee8recipes.chapter02.recipe02_15;

/**
 * Recipe 2-15:  Escaping EL in a Page
 * @author juneau
 */
public class ElBean implements java.io.Serializable {
    private String myProperty;
    
    public ElBean(){
        myProperty = "Test Value";
    }

    /**
     * @return the myProperty
     */
    public String getMyProperty() {
        return myProperty;
    }

    /**
     * @param myProperty the myProperty to set
     */
    public void setMyProperty(String myProperty) {
        this.myProperty = myProperty;
    }
    
    
}
