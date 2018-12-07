/*
 * Converts text into XML
 */
package org.javaee8recipes.chapter15.recipe15_07;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author juneau
 */
@XmlRootElement(name="messageWrapperList")
@XmlSeeAlso(String.class)
public class MessageWrapperList extends ArrayList<String> {
    private static final long serialVersionUID = 1L;

    public MessageWrapperList(){
        super();
    }
    
    public MessageWrapperList(Collection<? extends String> message){
        super(message);
    }
    
    @XmlElement(name="messageList")
    public List<String> getMessageList() {
        return this;
    }
    
    public void setMessageList(List<String> messages) {
        this.addAll(messages);
    }
}
