package org.javaee8recipes.chapter14.recipe14_03;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * UNCOMMENT THE @Resource lines after creating the factory and queue within
 * the application server, as per Recipe 14-01
 * @author juneau
 */
@Named(value = "example14_03")
@SessionScoped
public class Example14_03 implements java.io.Serializable {

  //  @Resource(name = "jms/javaEERecipesConnectionFactory")
    private ConnectionFactory connectionFactory;
  //  @Resource(lookup = "jms/javaEERecipesQueue")
    Queue inboundQueue;
    // @Resource(name = "jms/javaEERecipesQueue")
    private Queue myQueue;
    private Connection connection;
    private String connectionString;

    @PostConstruct
    public void createConnection() {
        try {
            connection = connectionFactory.createConnection();

        } catch (JMSException ex) {
            Logger.getLogger(Example14_03.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void closeConnection() {
        try {
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(Example14_03.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Context getContext() throws NamingException {

        Context jndiContext = new InitialContext();
        return jndiContext;

    }

    /**
     * Sending a JMS Message using the Standard API
     */
    public void sendMessage() {
        if (connection != null) {
            System.out.println("Creating Session");
            try(Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                ) {
                
                System.out.println("Creating producer");
                myQueue = (Queue) getContext().lookup("jms/javaEERecipesQueue");
                MessageProducer producer = session.createProducer(myQueue);
                System.out.println("Creating message");
                TextMessage message = session.createTextMessage();
                message.setText("Java EE 7 Is the Best!");
                System.out.println("Sending message");
                producer.send(message);
                System.out.println("Message sent, closing session");
                producer.close();

                setConnectionString("Message Successfully Sent to Queue");

            } catch (NamingException | JMSException ex) {
                System.out.println(ex);
                setConnectionString("Session not created and message not sent");
            }
        } else {
            setConnectionString("No connection available");
        }
    }

    public String sendMessageNew() {
        String response = "Message Not Sent..";
        try (JMSContext context = connectionFactory.createContext();) {
            StringBuilder message = new StringBuilder();
            message.append("Java EE 7 Is the Best!");
            context.createProducer().send(inboundQueue, message.toString());
            response = "Message Sent...";
        }
        return response;
    }

    /**
     * @return the connectionString
     */
    public String getConnectionString() {
        sendMessage();
        return connectionString;
    }

    /**
     * @param connectionString the connectionString to set
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
}
