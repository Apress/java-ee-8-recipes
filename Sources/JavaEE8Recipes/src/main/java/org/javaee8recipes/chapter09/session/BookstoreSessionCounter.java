package org.javaee8recipes.chapter09.session;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.ConcurrencyManagement;
import static javax.ejb.ConcurrencyManagementType.CONTAINER;

/**
 * Recipe 9-10 - Singleton Stateless Session Bean
 *
 * @author juneau
 */
@Singleton
@ConcurrencyManagement(CONTAINER)
public class BookstoreSessionCounter {

    private int numberOfSessions;

    /**
     * Initialize the Bean
     */
    @PostConstruct
    public void init() {
        // Initialize bean here
        System.out.println("Initalizing bean...");
    }

    public BookstoreSessionCounter() {
        numberOfSessions = 0;
    }

    /**
     * @return the numberOfSessions
     */
    public int getNumberOfSessions() {
        numberOfSessions++;
        return numberOfSessions;
    }

    /**
     * @param numberOfSessions the numberOfSessions to set
     */
    public void setNumberOfSessions(int numberOfSessions) {
        this.numberOfSessions = numberOfSessions;
    }
}
