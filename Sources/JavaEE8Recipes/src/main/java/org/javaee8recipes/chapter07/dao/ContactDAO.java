package org.javaee8recipes.chapter07.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.javaee8recipes.chapter07.Contact;
import org.javaee8recipes.chapter07.CreateConnection;

/**
 *
 * @author juneau
 */
public class ContactDAO implements java.io.Serializable {

    public ContactDAO() {
    }

    public List<Contact> queryContacts() {
        String qry = "select id,  first, last, email, password, description, "
                + "occupation, receivenotifications, gender from  Contact";
        
        List<Contact> contacts = new ArrayList();
        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(qry);) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String first = rs.getString("FIRST");
                String last = rs.getString("LAST");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                String description = rs.getString("DESCRIPTION");
                String occupation = rs.getString("OCCUPATION");
                String receive = rs.getString("RECEIVENOTIFICATIONS");
                String gender = rs.getString("GENDER");
                Contact  Contact = new  Contact(id,
                                               first,
                                               last,
                                               email,
                                               password,
                                               description,
                                               occupation,
                                               receiveNotificationFlag(receive),
                                               gender);
                 contacts.add( Contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
            return contacts;

        

    }
    
    private boolean receiveNotificationFlag(String receiveNotifications){
        if (receiveNotifications.toUpperCase().equals("Y")){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Queries the database for a particular Contact based upon ID and returns
     * the Contact object if found.
     * @param id
     * @return 
     */
    public  Contact performFind(int id){
        String qry = "SELECT ID, FIRST, LAST, EMAIL, PASSWORD, DESCRIPTION, OCCUPATION, "
                    + "RECEIVENOTIFICATIONS, GENDER "
                    + "FROM  CONTACT " 
                    + "WHERE ID = ?";

        Contact  contact = null;
        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery();) {
            if (rs.next()) {
                int  contactId = rs.getInt("ID");
                String first = rs.getString("FIRST");
                String last = rs.getString("LAST");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                String occupation = rs.getString("OCCUPATION");
                String receiveNotes = rs.getString("RECEIVENOTIFICATIONS");
                String gender = rs.getString("GENDER");
                String description = rs.getString("DESCRIPTION");
                contact = new  Contact(contactId,
                                        first,
                                        last,
                                        email,
                                        password,
                                        description,
                                        occupation,
                                        receiveNotificationFlag(receiveNotes),
                                        gender);
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
            return contact;

        
    }
    
    /**
     * Queries the database for a particular contact based upon first and last name
     * and returns a list of Contact objects if found.
     * @param id
     * @return 
     */
    public List<Contact> performFind(String first, String last){
        String qry = "SELECT ID,  FIRST, LAST, EMAIL, PASSWORD, DESCRIPTION, "
                    + "OCCUPATION, RECEIVENOTIFICATIONS, GENDER "
                    + "FROM  CONTACT " 
                    + "WHERE FIRST = ? "
                    + "AND  LAST = ?";

        List<Contact> contactList = new ArrayList();
        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, first.toUpperCase());
            stmt.setString(2,  last.toUpperCase());
            try (ResultSet rs = stmt.executeQuery();) {
            
            
            while (rs.next()) {
                int  contactId = rs.getInt("ID");
                String firstName = rs.getString("FIRST");
                String lastName = rs.getString("LAST");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                String occupation = rs.getString("OCCUPATION");
                String receiveNotes = rs.getString("RECEIVENOTIFICATIONS");
                String gender = rs.getString("GENDER");
                String description = rs.getString("DESCRIPTION");
                Contact contact = new  Contact(contactId,
                                        firstName,
                                        lastName,
                                        email,
                                        password,
                                        description,
                                        occupation,
                                        receiveNotificationFlag(receiveNotes),
                                        gender);
                contactList.add(contact);
            }
            }  
        } catch (SQLException e) {
            e.printStackTrace();
        } 
            return contactList;

        
    }

    /**
     */
    private void performCreate(String first, String last, String email,
                               String password, String description, String occupation,
                               String receivenotifications, String gender) {
        String sql = "INSERT INTO  Contact VALUES("
                + " Contact_S.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, first.toUpperCase());
            stmt.setString(2, last.toUpperCase());
            stmt.setString(3, email.toLowerCase());
            stmt.setString(4, password);
            stmt.setString(5, description);
            stmt.setString(6, occupation);
            stmt.setString(7, receivenotifications);
            stmt.setString(8, gender);
            // Returns row-count or 0 if not successful
            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("-- Record created --");
            } else {
                System.out.println("!! Record NOT Created !!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    /**
     * Update record in the  Contact table
     * 
     * @param id
     * @param  ContactNumber
     * @param title
     * @param description 
     */
    private void performUpdate(int id, String first, String last, String email,
                               String password, String description, String occupation,
                               String receivenotifications, String gender) {
        String sql = "UPDATE contact "
                + "SET FIRST = ?,"
                + "    LAST = ?,"
                + "    EMAIL = ?, "
                + "    PASSWORD = ?, "
                + "    DESCRIPTION = ?, "
                + "    OCCUPATION = ?, "
                + "    RECEIVENOTIFICATIONS = ?, "
                + "    GENDER = ? "
                + "WHERE ID = ?";
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, first.toUpperCase());
            stmt.setString(2, last.toUpperCase());
            stmt.setString(3, email.toLowerCase());
            stmt.setString(4, password);
            stmt.setString(5, description);
            stmt.setString(6, occupation);
            stmt.setString(7, receivenotifications);
            stmt.setString(8, gender);
            stmt.setInt(9, id);
            int result = stmt.executeUpdate(sql);
            if (result > 0) {
                System.out.println("-- Record Updated --");

            } else {
                System.out.println("!! Record NOT Updated !!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    /**
     * Delete record from the CONTACT table
     * @param id 
     */
    private void performDelete(int id) {
        String sql = "DELETE FROM contact WHERE ID = ?";
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("-- Record Deleted --");
            } else {
                System.out.println("!! Record NOT Deleted!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
    /**
     * Returns the next ID in the  Contact_S sequence
     * @return 
     */
    public int getNextId(){
        String qry = "select  Contact_s.currval as ID from dual";

        int returnId = -1;
        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(qry);) {
            while (rs.next()) {
                int  Contact_id = rs.getInt("ID");
                returnId =  Contact_id + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
            return returnId;

        
    }
    
    /**
     * Facade method for inserting  Contact objects into the  Contact table
     * @param  Contact 
     */
    public void insert(Contact  contact){
        performCreate(contact.getFirst(),
                    contact.getLast(),
                    contact.getEmail(),
                    contact.getPassword(),
                    contact.getDescription(),
                    contact.getOccupation(),
                    "Y",
                    contact.getGender());
    }
    
    /**
     * Facade method for updating Contact objects in the  CONTACT table
     * @param  Contact 
     */
    public void update(Contact contact){
        this.performUpdate(contact.getId(),
                    contact.getFirst(),
                    contact.getLast(),
                    contact.getEmail(),
                    contact.getPassword(),
                    contact.getDescription(),
                    contact.getOccupation(),
                    "Y",
                    contact.getGender());
    }
    
    /**
     * Facade method for deleting  Contact objects from the  CONTACT table
     * @param args 
     */
    public void delete( Contact  contact){
        performDelete(contact.getId());
    }

    
}
