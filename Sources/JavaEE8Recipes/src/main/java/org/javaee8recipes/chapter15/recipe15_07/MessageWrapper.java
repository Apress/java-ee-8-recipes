/*
 * Converts text into XML
 */
package org.javaee8recipes.chapter15.recipe15_07;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juneau
 */
@XmlRootElement(name="message")
public class MessageWrapper {
    private String message;
    
    public MessageWrapper(){
        
    }
    
    public MessageWrapper(String message){
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
