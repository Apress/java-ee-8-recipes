
package org.javaee8recipes.chapter02.recipe02_04;

/**
 * Recipe 2-4: Yielding and Setting Values
 * @author juneau
 */
public class EasyBean implements java.io.Serializable {
    private String fieldValue;
    
    public EasyBean(){
        fieldValue = null;
    }

    /**
     * @return the fieldValue
     */
    public String getFieldValue() {
        return fieldValue;
    }

    /**
     * @param fieldValue the fieldValue to set
     */
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
    
}
