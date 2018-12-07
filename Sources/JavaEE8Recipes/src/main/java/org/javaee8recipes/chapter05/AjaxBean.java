
package org.javaee8recipes.chapter05;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.search.SearchExpressionContext;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

/**
 *
 * @author Juneau
 */
@Named(value = "ajaxBean")
@SessionScoped
public class AjaxBean implements Serializable {
    
//    @Inject
//    FacesContext context;

    private Part file = null;
    private String value1;
    private String value2;
    private String status;

    /**
     * Creates a new instance of AjaxBean
     */
    public AjaxBean() {
    }

    public void sendMessage() {
        System.out.println("Message Sent Successfully");
    }

    public void uploadFile() {
        
        try(InputStream is = file.getInputStream();) {
            byte[] b = new byte[1024];
            is.read(b);
            String fileName = file.getName();
            FileOutputStream os = new FileOutputStream("/Java_Dev/" + fileName);

        } catch (IOException ex) {
            Logger.getLogger(AjaxBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void findById() {
        FacesContext context = FacesContext.getCurrentInstance();
        SearchExpressionContext searchContext = 
            SearchExpressionContext.createSearchExpressionContext(context, context.getViewRoot());
         
        context.getApplication()
                .getSearchExpressionHandler()
                .resolveComponent(
                   searchContext, 
                   "passwordConfirm", 
                   (ctx, target) -> out.print(target.getId()));
    }
    
    private String getFileName(Part part) {
    String partHeader = part.getHeader("content-disposition");
   
    for (String cd : part.getHeader("content-disposition").split(";")) {
      if (cd.trim().startsWith("filename")) {
        return cd.substring(cd.indexOf('=') + 1).trim()
            .replace("\"", "");
      }
    }
    return null;

  }

    /**
     * @return the file
     */
    public Part getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(Part file) {
        this.file = file;
    }

    /**
     * @return the value1
     */
    public String getValue1() {
        return value1;
    }

    /**
     * @param value1 the value1 to set
     */
    public void setValue1(String value1) {
        this.value1 = value1;
    }

    /**
     * @return the value2
     */
    public String getValue2() {
        return value2;
    }

    /**
     * @param value2 the value2 to set
     */
    public void setValue2(String value2) {
        this.value2 = value2;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        status = "Value 1: " + value1 + " and Value2: " + value2;
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void process(){
        System.out.println("Processing...");
    }
}
