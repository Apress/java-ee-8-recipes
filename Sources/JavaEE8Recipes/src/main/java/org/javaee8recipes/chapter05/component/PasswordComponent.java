
package org.javaee8recipes.chapter05.component;


import java.io.IOException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.component.UIInput;

/**
 *
 * @author juneau
 */
@FacesComponent("components.PasswordComponent")
public class PasswordComponent extends UIComponentBase {
    
    UIInput password = null;
    UIInput confirmPassword = null;
 
    @Override
    public String getFamily() {        
        return "org.javaee8recipes.chapter06.component";
    }
 
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
 
        String value = (String) getAttributes().get("value");
 
        if (value != null) {        
            ResponseWriter writer = context.getResponseWriter();
         //   writer.write(password);
            writer.write("<br/><br/>");
           // writer.write(confirmPassword);
            
        }
    }
}
