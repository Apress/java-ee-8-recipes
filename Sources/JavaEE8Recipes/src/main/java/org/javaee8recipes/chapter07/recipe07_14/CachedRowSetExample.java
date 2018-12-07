
package org.javaee8recipes.chapter07.recipe07_14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.spi.SyncProviderException;
import org.javaee8recipes.chapter07.CreateConnection;

public class CachedRowSetExample {


    public static CachedRowSet crs = null;

    public static void main(String[] args) {
        boolean successFlag = false;
        CreateConnection.loadProperties();
        try(Connection conn = CreateConnection.getConnection();) {
              
    // Perform Scrollable Query
            queryWithRowSet(conn);
            updateData();
            syncWithDatabase(conn);
        } catch (java.sql.SQLException ex) {
            System.out.println(ex);
        } 
    }

    /**
     * Call this method to synchronize the data that has been used in the
     * CachedRowSet with the database
     */
    public static void syncWithDatabase(Connection conn) {
        try {
            crs.acceptChanges(conn);
        } catch (SyncProviderException ex) {
        // If there is a conflict while synchronizing, this exception
        // will be thrown.
            ex.printStackTrace();
        } finally {
        // Clean up resources by closing CachedRowSet
            if (crs != null) {
                try {
                    crs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void queryWithRowSet(Connection conn) {
        RowSetFactory factory;
        try {
        // Create a new RowSetFactory
            factory = RowSetProvider.newFactory();
        // Create a CachedRowSet object using the factory
            crs = factory.createCachedRowSet();
        // Alternatively populate the CachedRowSet connection settings
        // crs.setUsername(createConn.getUsername());
        // crs.setPassword(createConn.getPassword());
        // crs.setUrl(createConn.getJdbcUrl());
        // Populate a query that will obtain the data that will be used
            crs.setCommand("select id, chapter_number, title, description, book_id from chapter");
        // Set key columns
            int[] keys = {1};
            crs.setKeyColumns(keys);
        // Execute query
            crs.execute(conn);
        // You can now work with the object contents in a disconnected state
            while (crs.next()) {
                System.out.println(crs.getString(2) + ": " + crs.getString(3)
                        + " - " + crs.getString(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean updateData() {
        boolean returnValue = false;
        try {

        // Move to the position before the first row in the result set
            crs.beforeFirst();
        // traverse result set
            while (crs.next()) {
        // If the chapter_number equals 1 then update
                if (crs.getInt("CHAPTER_NUMBER") == 1) {
                    System.out.println("updating Chapter 1");
                    crs.updateString("TITLE", "Subject to change");
                    crs.updateRow();
                }
            }
            returnValue = true;
        // Move to the position before the first row in the result set
            crs.beforeFirst();
        // traverse result set to see changes
            while (crs.next()) {
                System.out.println(crs.getString(2) + ": " + crs.getString(3));
            }
        } catch (SQLException ex) {
            returnValue = false;
            ex.printStackTrace();
        }
        return returnValue;
    }
}
