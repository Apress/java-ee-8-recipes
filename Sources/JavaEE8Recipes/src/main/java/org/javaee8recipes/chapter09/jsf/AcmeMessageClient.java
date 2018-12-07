package org.javaee8recipes.chapter09.jsf;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author Juneau
 */
@Named
public class AcmeMessageClient implements java.io.Serializable {
    // Uncomment after the jms/javaeeRecipesConnectionFactory JMS resource has
    // been created within the application server container (Recipe 14-1)
    //@Resource(mappedName = "jms/javaeeRecipesConnectionFactory")
    private static ConnectionFactory connectionFactory;
    // Uncomment after the jms/Queue JMS resource has been created within the
    // application server container (Recipe 14-1)
    //@Resource(mappedName = "jms/Queue")
    private static Queue queue;

    public void sendMessage() {

        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();

            message.setText("This is a test message");
            System.out.println("Sending message: " + message.getText());
            messageProducer.send(message);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Sent", null);

            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } catch (javax.jms.JMSException ex) {
            System.out.println(ex);
        }
    }
}
