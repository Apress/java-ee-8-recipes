package org.javaee8recipes.chapter07.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.javaee8recipes.chapter07.Book;
import org.javaee8recipes.chapter07.CreateConnection;

/**
 * Chapter 7 - Data Access Object for the BOOK database table
 * @author juneau
 */
public class BookDAO implements java.io.Serializable {

   
    public BookDAO() {
    }

    /**
     * Query the database for all books
     */
    public List<Book> queryBooks() {
        String qry = "select id, title, image, description from book";

        List books = new ArrayList();
        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(qry);) {
            while (rs.next()) {
                int book_id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String image = rs.getString("IMAGE");
                String description = rs.getString("DESCRIPTION");
                Book book = new Book(book_id,
                                     title,
                                     image,
                                     description);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
            return books;
        

    }
    
    /**
     * Queries the database for a particular book based upon ID and returns
     * the Book object if found.
     * @param id
     * @return 
     */
    public Book performFind(int id){
        String qry = "SELECT ID, TITLE, IMAGE, DESCRIPTION " 
                    + "FROM BOOK " 
                    + "WHERE ID = ?";
        
        Book book = null;
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery();) {
 
            if (rs.next()) {
                int book_id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String image = rs.getString("IMAGE");
                String description = rs.getString("DESCRIPTION");
                book = new Book(book_id,
                                title,
                                image,
                                description);
            }
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        } 
            return book;

        
    }
    
    /**
     * Queries the database for a particular book based upon title 
     * and returns a list of Book objects if found.
     * @param id
     * @return 
     */
    public Book performFind(String title){
        String qry = "SELECT ID, TITLE, IMAGE, DESCRIPTION " 
                    + "FROM BOOK " 
                    + "WHERE TITLE = ?";

        Book book = null;
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, title.toUpperCase());
            try (ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                int book_id = rs.getInt("ID");
                String book_title = rs.getString("TITLE");
                String image = rs.getString("IMAGE");
                String description = rs.getString("DESCRIPTION");
                book = new Book(book_id,
                                           book_title,
                                           image,
                                           description);

            }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
            return book;

        
    }

    /**
     * Create a Book object and INSERT it into the BOOK table.
     *
     * @param first
     * @param last
     * @param bio
     */
    private void performCreate(String title, String image, String description) {
        String sql = "INSERT INTO BOOK VALUES("
                + "BOOK_S.NEXTVAL, ?, ?, ?)";
        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title.toUpperCase());
            stmt.setString(2, image);
            stmt.setString(3, description);
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

    private void performUpdate(int id, String title, String image, String description) {
        String sql = "UPDATE BOOK "
                + "SET title = ?,"
                + "   image = ?,"
                + "   description = ? "
                + "WHERE ID = ?";
        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title.toUpperCase());
            stmt.setString(2, image);
            stmt.setString(3, description);
            stmt.setInt(4, id);
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

    private void performDelete(int id) {
        String sql = "DELETE FROM BOOK WHERE ID = ?";
        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int result = stmt.executeUpdate(sql);
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
     * Returns the next ID in the BOOK_S sequence
     * @return 
     */
    public int getNextId(){
        String qry = "select book_s.currval as ID from dual";

        int returnId = -1;
        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(qry);) {
            while (rs.next()) {
                int author_id = rs.getInt("ID");
                returnId = author_id + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
            return returnId;

        
    }
    
    /**
     * Facade method for inserting Book objects into the BOOK table
     * @param book 
     */
    public void insert(Book book){
        performCreate(book.getTitle(), 
                      book.getImage(),
                      book.getDescription());
    }
    
    /**
     * Facade method for updating Book objects in the BOOK table
     * @param author 
     */
    public void update(Book book){
        this.performUpdate(book.getId(), book.getTitle(), book.getImage(), book.getDescription());
    }
    
    /**
     * Facade method for deleting Book objects from the BOOK table
     * @param args 
     */
    public void delete(Book book){
        performDelete(book.getId());
    }

    
}
