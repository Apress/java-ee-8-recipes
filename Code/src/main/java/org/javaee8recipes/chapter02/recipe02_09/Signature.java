
package org.javaee8recipes.chapter02.recipe02_09;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Recipe 2-9:  Creating a Custom JSP Tag
 * @author juneau
 */
public class Signature extends SimpleTagSupport {
    
    private String authorName = null;

    /**
     * @param authorName the authorName to set
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    
    @Override
    public void doTag() throws JspException {   
        PageContext pageContext = (PageContext) getJspContext();   
        JspWriter out = pageContext.getOut();   
           
        try {   
            if(authorName != null){
                out.println("Written by " + authorName);
                out.println("<br/>");
            }   
            out.println("Published by Apress");   
               
        } catch (Exception e) {   
            System.out.println(e);
        }   
           
    }   
    
}
