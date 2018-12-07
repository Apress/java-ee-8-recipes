package org.javaee8recipes.chapter14.recipe14_05;

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
 * UNCOMMENT the @Resource lines after creating the factory and queue within
 * the server, as per Recipe 14-01
 * @author juneau
 */
@Named(value = "example14_05")
@ApplicationScoped
public class Example14_05 {

    //@Resource(name = "jms/javaEERecipesConnectionFactory")
    private ConnectionFactory connectionFactory;
    //@Resource(name = "jms/javaEERecipesQueue")
    private Queue myQueue;
    private Connection connection;
    private String displayMessage = "No messages as yet...";

    /**
     * Creates a new instance of Example13_04
     */
    public Example14_05() {
    }

    public void createConnection() {
        try {
            connection = connectionFactory.createConnection();

        } catch (JMSException ex) {
            Logger.getLogger(Example14_05.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(Example14_05.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Context getContext() throws NamingException {
        Context jndiContext = new InitialContext();
        return jndiContext;

    }

    public void sendMessage1() {
        if (connection != null) {
            try (Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                    MessageProducer producer = session.createProducer(myQueue);) {

                System.out.println("Creating message");
                TextMessage message = session.createTextMessage();
                message.setText("Java EE 7 Is the Best!");
                message.setStringProperty("TYPE", "JAVAEE");
                System.out.println("Sending message");
                producer.send(message);

            } catch (JMSException ex) {
                System.out.println(ex);

            }
        }

    }

    public void sendMessage2() {
        if (connection != null) {
            try (Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                    MessageProducer producer = session.createProducer(myQueue);) {

                System.out.println("Creating message");
                TextMessage message2 = session.createTextMessage();
                message2.setText("Java SE 7 Is Great!");
                message2.setStringProperty("TYPE", "JAVASE");
                System.out.println("Sending message");
                producer.send(message2);

            } catch (JMSException ex) {
                System.out.println(ex);

            }
        }

    }

    public void receiveMessage() {
        boolean stopReceivingMessages = false;
        String selector = "TYPE = 'JAVAEE'";
        try(Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(myQueue, selector);) {

            connection.start();

            while (!stopReceivingMessages) {
                System.out.println("Receiving message");
                Message inMessage = consumer.receive();
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

        } catch (JMSException ex) {
            System.out.println(ex);
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
