package org.javaee8recipes.chapter07.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.javaee8recipes.chapter07.Author;
import org.javaee8recipes.chapter07.CreateConnection;

/**
 *
 * @author juneau
 */
public class AuthorDAO implements java.io.Serializable {

    public AuthorDAO() {
    }

    public void queryBookAuthor() {
        String qry = "select id, first, last, bio from book_author";
        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(qry);) {


            while (rs.next()) {
                int author_id = rs.getInt("ID");
                String first_name = rs.getString("FIRST");
                String last_name = rs.getString("LAST");
                String bio = rs.getString("BIO");
                System.out.println(author_id + "\t" + first_name
                        + " " + last_name + "\t" + bio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Author> obtainCompleteAuthorList() {
        String qry = "select id, first, last, bio from book_author";
        List<Author> authors = new ArrayList();
        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(qry);) {
            while (rs.next()) {
                int author_id = rs.getInt("ID");
                String first_name = rs.getString("FIRST");
                String last_name = rs.getString("LAST");
                String bio = rs.getString("BIO");
                Author author = new Author(author_id, first_name,
                        last_name, bio);
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    /**
     * Queries the database for a particular author based upon ID and returns
     * the Author object if found.
     *
     * @param id
     * @return
     */
    public Author performFind(int id) {
        String qry = "SELECT ID, LAST, FIRST, BIO "
                + "FROM BOOK_AUTHOR "
                + "WHERE ID = ?";

        Author author = null;
        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery();) {


                if (rs.next()) {
                    int author_id = rs.getInt("ID");
                    String first_name = rs.getString("FIRST");
                    String last_name = rs.getString("LAST");
                    String bio = rs.getString("BIO");
                    author = new Author(author_id,
                            first_name,
                            last_name,
                            bio);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;


    }

    /**
     * Queries the database for a particular author based upon first and last
     * name and returns a list of Author objects if found.
     *
     * @param id
     * @return
     */
    public List<Author> performFind(String first, String last) {
        String qry = "SELECT ID, LAST, FIRST, BIO "
                + "FROM BOOK_AUTHOR "
                + "WHERE LAST = ? "
                + "AND FIRST = ?";

        List authorList = new ArrayList();
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, last.toUpperCase());
            stmt.setString(2, first.toUpperCase());
            try (ResultSet rs = stmt.executeQuery();) {

                while (rs.next()) {
                    int author_id = rs.getInt("ID");
                    String first_name = rs.getString("FIRST");
                    String last_name = rs.getString("LAST");
                    String bio = rs.getString("BIO");
                    Author author = new Author(author_id,
                            first_name,
                            last_name,
                            bio);
                    authorList.add(author);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;


    }

    /**
     * Do not use this method in production, instead make use of
     * PreparedStatements
     *
     * @param first
     * @param last
     * @param bio
     */
    private void performCreate(String first, String last, String bio) {
        String sql = "INSERT INTO BOOK_AUTHOR VALUES("
                + "BOOK_AUTHOR_S.NEXTVAL, ?, ?, ?)";
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, last.toUpperCase());
            stmt.setString(2, first.toUpperCase());
            stmt.setString(3, bio.toUpperCase());


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

    private void performUpdate(int id, String first, String last, String bio) {
        String sql = "UPDATE BOOK_AUTHOR "
                + "SET bio = ?,"
                + "   last = ?,"
                + "   first = ? "
                + "WHERE ID = ?";
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bio.toUpperCase());
            stmt.setString(2, last.toUpperCase());
            stmt.setString(3, first.toUpperCase());
            stmt.setInt(4, id);

                int result = stmt.executeUpdate();
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
        String sql = "DELETE FROM BOOK_AUTHOR WHERE ID = ?";
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
     * Returns the next ID in the BOOK_AUTHOR_S sequence
     *
     * @return
     */
    public int getNextId() {
        String qry = "select book_author_s.currval as ID from dual";

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
     * Facade method for inserting Author objects into the BOOK_AUTHOR table
     *
     * @param author
     */
    public void insert(Author author) {
        performCreate(author.getFirst(),
                author.getLast(),
                author.getBio());
    }

    /**
     * Facade method for updating Author objects in the BOOK_AUTHOR table
     *
     * @param author
     */
    public void update(Author author) {
        this.performUpdate(author.getId(), author.getFirst(), author.getLast(), author.getBio());
    }

    /**
     * Facade method for deleting Author objects from the BOOK_AUTHOR table
     *
     * @param args
     */
    public void delete(Author author) {
        performDelete(author.getId());
    }

    public static void main(String[] args) {
        AuthorDAO authorDao = new AuthorDAO();
        authorDao.queryBookAuthor();
        authorDao.performCreate("Joe", "Blow", "N/A");

        // Find any author named Joe Blow and store in authList
        List<Author> authList = authorDao.performFind("Joe", "Blow");
        // Update the BIO for any author named Joe Blow
        for (Author auth : authList) {
            auth.setBio("New Bio");
            authorDao.update(auth);
        }
        authorDao.queryBookAuthor();

        // Delete any author named Joe Blow
        for (Author auth : authList) {
            authorDao.delete(auth);
        }
    }
}
