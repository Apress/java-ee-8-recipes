package org.javaee8recipes.chapter07.recipe07_15;

/**
 * Recipe 7-15
 * @author juneau
 */
import com.sun.rowset.JoinRowSetImpl;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import org.javaee8recipes.chapter07.CreateConnection;

public class JoinRowSetExample {

    public static CreateConnection createConn;
    public static CachedRowSet bookAuthors = null;
    public static CachedRowSet authorWork = null;
    public static JoinRowSet jrs = null;

    public static void main(String[] args) {
        boolean successFlag = false;
        CreateConnection.loadProperties();
        try(Connection conn = CreateConnection.getConnection();) {

        // Perform Scrollable Query
            queryBookAuthor(conn);
            queryAuthorWork(conn);
            joinRowQuery();
        } catch (java.sql.SQLException ex) {
            System.out.println(ex);
        } finally {
            
            if (bookAuthors != null) {
                try {
                    bookAuthors.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (authorWork != null) {
                try {
                    authorWork.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (jrs != null) {

                try {
                    jrs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void queryBookAuthor(Connection conn) {
        RowSetFactory factory;
        try {
        // Create a new RowSetFactory
            factory = RowSetProvider.newFactory();
        // Create a CachedRowSet object using the factory
            bookAuthors = factory.createCachedRowSet();
        // Alternatively opulate the CachedRowSet connection settings
        // crs.setUsername(createConn.getUsername());
        // crs.setPassword(createConn.getPassword());
        // crs.setUrl(createConn.getJdbcUrl());
        // Populate a query that will obtain the data that will be used
            bookAuthors.setCommand("SELECT ID, LAST, FIRST FROM BOOK_AUTHOR");
            bookAuthors.execute(conn);
        // You can now work with the object contents in a disconnected state
            while (bookAuthors.next()) {
                System.out.println(bookAuthors.getString(1) + ": " + bookAuthors.getString(2)
                        + ", " + bookAuthors.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void queryAuthorWork(Connection conn) {
        RowSetFactory factory;
        try {

        // Create a new RowSetFactory
            factory = RowSetProvider.newFactory();
        // Create a CachedRowSet object using the factory
            authorWork = factory.createCachedRowSet();
        // Alternatively opulate the CachedRowSet connection settings
        // crs.setUsername(createConn.getUsername());
        // crs.setPassword(createConn.getPassword());
        // crs.setUrl(createConn.getJdbcUrl());
        // Populate a query that will obtain the data that will be used
            authorWork.setCommand(
            "SELECT ID, AUTHOR_ID, BOOK_ID FROM AUTHOR_WORK");
            authorWork.execute(conn);
        // You can now work with the object contents in a disconnected state
            while (authorWork.next()) {
                System.out.println(authorWork.getString(1) + ": " + authorWork.getInt(2)
                        + " - " + authorWork.getInt(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void joinRowQuery() {
        try {
        // Create JoinRowSet
            jrs = new JoinRowSetImpl();
        // Add RowSet & Corresponding Keys
            jrs.addRowSet(bookAuthors, 1);
            jrs.addRowSet(authorWork, 2);
        // Traverse Results
            while (jrs.next()) {
                System.out.println(jrs.getString("BOOK_ID") + " - "
                        + jrs.getString("FIRST") + " "
                        + jrs.getString("LAST"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
