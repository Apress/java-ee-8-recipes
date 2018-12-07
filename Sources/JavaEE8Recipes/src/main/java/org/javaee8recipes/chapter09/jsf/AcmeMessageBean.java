
package org.javaee8recipes.chapter09.jsf;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * In order to run this example, you must first create JMS resources within
 * the application server container.  Once created, uncomment the annotation
 * below and re-deploy the project.
 * 
 * @author Juneau
 */
// Uncomment after the jms/Queue JMS resource has been created within the
// application server container (Recipe 14-1)
//@MessageDriven(mappedName="jms/Queue", activationConfig = {
//    @ActivationConfigProperty(propertyName = "destinationType",
//                              propertyValue = "javax.jms.Queue")
//})
public class AcmeMessageBean implements MessageListener {
    
    public AcmeMessageBean(){
        
    }

    @Override
    public void onMessage(Message msg) {
        if(msg != null){
            performExtraProcessing();
            System.out.println("Message has been received: " + msg);
        } else {
            System.out.println("No message received");
        }
    }
    
    public void performExtraProcessing(){
        System.out.println("This method could perform extra processing");
    }
     
}
