
package org.javaee8recipes.chapter18.recipe18_06;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.javaee8recipes.common.CreateConnection;

/**
 *
 * @author Juneau
 */
public class MessagePrinter implements Runnable {

    @Override
    public void run() {
        printMessage();
    }
    
    public void printMessage(){
        System.out.println("Here we are performing some work...");
    }
}
