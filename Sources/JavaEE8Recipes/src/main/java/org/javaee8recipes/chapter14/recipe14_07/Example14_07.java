
package org.javaee8recipes.chapter14.recipe14_07;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * UNCOMMENT the @Resource lines after creating the factory and topic within the
 * application server, as per the recipes within the book. 
 * @author juneau
 */
@Named(value="example14_07")
@ApplicationScoped
public class Example14_07 {

    //@Resource(name = "jms/javaEERecipesConnectionFactory")
    private TopicConnectionFactory connectionFactory;
    //@Resource(name = "jms/javaEERecipesTopic")
    private Topic myTopic;
    private TopicConnection connection;
    private String displayMessage = "No messages as yet...";
    /**
     * Creates a new instance of Example13_04
     */
    public Example14_07() {
    }


    public void createConnection() {
        try {
            connection = (TopicConnection) connectionFactory.createConnection();
            connection.setClientID("durable");
        } catch (JMSException ex) {
            Logger.getLogger(Example14_07.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void closeConnection() {
        try {
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(Example14_07.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Context getContext() throws NamingException {
        Context jndiContext = new InitialContext();
        return jndiContext;

    }
    
    public void createTopicSubscriber(){
        try {
            createConnection();
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            myTopic = (Topic) getContext().lookup("jms/javaEERecipesTopic");
            TopicSubscriber subscriber = session.createDurableSubscriber(myTopic, "javaEERecipesSub");
            connection.close();
        } catch (javax.naming.NamingException | JMSException ex) {
            Logger.getLogger(Example14_07.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void sendMessage() {
        try {
            createConnection();
                System.out.println("Creating session");
                TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
                System.out.println("Creating message");
                TextMessage message  = session.createTextMessage();
                message.setText("Java EE 7 Is the Best!");
                message.setStringProperty("TYPE", "JAVAEE");
                
                System.out.println("Creating producer");
                myTopic = (Topic) getContext().lookup("jms/javaEERecipesTopic");
                TopicPublisher publisher = session.createPublisher(myTopic);
                publisher.setDeliveryDelay(1000);
                System.out.println("Sending message");
                publisher.publish(message);
                
                
                
                System.out.println("Message sent, closing session");
                publisher.close();
                session.close();
                connection.close();

        } catch (NamingException | JMSException ex) {
            Logger.getLogger(Example14_07.class.getName()).log(Level.SEVERE, null, ex);
        
          
        }

    }

      
    public void receiveMessage() {
        boolean stopReceivingMessages = false;
        try {
            createConnection();
            System.out.println("Creating session to receive message");
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            myTopic = (Topic) getContext().lookup("jms/javaEERecipesTopic");
            System.out.println("Setting up consumer");
            
            String selector = "TYPE = 'JAVAEE'";
            TopicSubscriber subscriber = session.createDurableSubscriber(myTopic, "javaEERecipesSub");
            connection.start();

            while (!stopReceivingMessages) {
                System.out.println("Receiving message");
                Message inMessage = subscriber.receive();
                if (inMessage != null) {
                    System.out.println(inMessage);
                    if (inMessage instanceof TextMessage) {
                        String messageStr = ((TextMessage) inMessage).getText();
                        System.out.println(messageStr);
                        setDisplayMessage(messageStr);
                    } else {
                        System.out.println("Message was of another type");
                        setDisplayMessage("Message was of another type");
                    }
                } else {
                    stopReceivingMessages = true;
                }
               
            }
            connection.stop();
            subscriber.close();
            
            session.close();
            closeConnection();
        } catch (NamingException | JMSException ex) {
            Logger.getLogger(Example14_07.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void unsubscribe(){
        try {
            createConnection();
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            session.unsubscribe("javaEERecipesSub");
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(Example14_07.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the displayMessage
     */
    public String getDisplayMessage() {
        return displayMessage;
    }

    /**
     * @param displayMessage the displayMessage to set
     */
    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}
