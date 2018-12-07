
package org.javaee8recipes.chapter18.recipe18_05;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.javaee8recipes.common.CreateConnection;

/**
 *
 * @author Juneau
 */
public class ScheduledLoggerExample implements Runnable {

    CreateConnection createConn = null;

    @Override
    public void run() {
        queryAuthors();
    }
    
    public void queryAuthors(){
        createConn = new CreateConnection();
        String qry = "select object(o) from BookAuthor o";
        createConn.loadProperties();
        try (Connection conn = createConn.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(qry);) {
            while (rs.next()) {
           // if new author, then alert
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
