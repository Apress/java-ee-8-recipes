
package org.javaee8recipes.chapter14.recipe14_02;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 *
 * @author juneau
 */
@Named(value = "example14_02")
@SessionScoped
public class Example14_02 implements java.io.Serializable{

    @Resource(name = "jms/javaEERecipesConnectionFactory")
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private String connectionString;

    @PostConstruct
    public void createConnection() {
        try {
            connection = connectionFactory.createConnection();

        } catch (JMSException ex) {
            Logger.getLogger(Example14_02.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void closeConnection() {
        try {
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(Example14_02.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createSession() {
        try {
            if (connection != null) {
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                session.close();
                setConnectionString("Session successfully created");
            } else {
                setConnectionString("No connection available");
            }

        } catch (JMSException ex) {
            Logger.getLogger(Example14_02.class.getName()).log(Level.SEVERE, null, ex);
            setConnectionString("Session not created");
        }

    }

    /**
     * @return the connectionString
     */
    public String getConnectionString() {
        createSession();
        return connectionString;
    }

    /**
     * @param connectionString the connectionString to set
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
}
