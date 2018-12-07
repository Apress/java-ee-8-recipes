package org.javaee8recipes.chapter07.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.javaee8recipes.chapter07.AuthorWork;
import org.javaee8recipes.chapter07.CreateConnection;

/**
 * Chapter 7 - Data Access Object for the AUTHOR_WORK database table
 *
 * @author juneau
 */
public class AuthorWorkDAO implements java.io.Serializable {

    public AuthorWorkDAO() {
    }

    /**
     * Query the database for all AuthorWork
     */
    public List<AuthorWork> queryAuthorWork() {
        String qry = "select id, author_id, book_id from author_work";
        List authorWork = new ArrayList();
        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(qry);) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                int authorId = rs.getInt("AUTHOR_ID");
                int bookId = rs.getInt("BOOK_ID");
                AuthorWork aw = new AuthorWork(id,
                        authorId,
                        bookId);
                authorWork.add(aw);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorWork;


    }

    /**
     * Queries the database for a particular authors work based upon ID and
     * returns the AuthorWork object if found.
     *
     * @param id
     * @return
     */
    public List<AuthorWork> performFind(int id) {
        String qry = "SELECT ID, AUTHOR_ID, BOOK_ID "
                + "FROM AUTHOR_WORK "
                + "WHERE BOOK_ID = ?";

        List<AuthorWork> aw = new ArrayList();
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery();) {


                while (rs.next()) {
                    int recordId = rs.getInt("ID");
                    int author_id = rs.getInt("AUTHOR_ID");
                    int bookId = rs.getInt("BOOK_ID");
                    AuthorWork awObj = new AuthorWork(recordId,
                            author_id,
                            bookId);
                    aw.add(awObj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aw;


    }

    public List<AuthorWork> performFindBooks(int authorId) {
        String qry = "SELECT ID, AUTHOR_ID, BOOK_ID "
                + "FROM AUTHOR_WORK "
                + "WHERE AUTHOR_ID = ?";

        List<AuthorWork> aw = new ArrayList();
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, authorId);
            try (ResultSet rs = stmt.executeQuery();) {

                if (rs.next()) {
                    int recordId = rs.getInt("ID");
                    int author_id = rs.getInt("AUTHOR_ID");
                    int bookId = rs.getInt("BOOK_ID");
                    AuthorWork awObj = new AuthorWork(recordId,
                            author_id,
                            bookId);
                    aw.add(awObj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aw;


    }

    /**
     * Create a AuthorWork object and INSERT it into the AUTHOR_WORK table.
     *
     * @param first
     * @param last
     * @param bio
     */
    private void performCreate(int authorId, int bookId) {
        String sql = "INSERT INTO AUTHOR_WORK VALUES("
                + "AUTHOR_WORK_S.NEXTVAL, ?, ?)";
        CreateConnection.loadProperties();
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, authorId);
            stmt.setInt(2, bookId);

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

    private void performUpdate(int id, int authorId, int bookId) {
        String sql = "UPDATE AUTHOR_WORK "
                + "SET id = ?, "
                + "   author_id = ? "
                + "WHERE ID = ?";
        try (Connection conn = CreateConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            stmt.setInt(2, authorId);
            stmt.setInt(3, id);
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
        String sql = "DELETE FROM AUTHOR_ID WHERE ID = ?";
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
     * Returns the next ID in the AUTHOR_WORK_S sequence
     *
     * @return
     */
    public int getNextId() {
        String qry = "select author_work_s.nextval as ID from dual";

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
     * Facade method for inserting AuthorWork objects into the AUTHOR_WORK table
     *
     * @param AuthorWork
     */
    public void insert(AuthorWork aw) {
        performCreate(aw.getAuthorId(),
                aw.getBookId());
    }

    /**
     * Facade method for updating AuthorWork objects in the AUTHOR_WORK table
     *
     * @param authorWork
     */
    public void update(AuthorWork aw) {
        this.performUpdate(aw.getId(), aw.getAuthorId(), aw.getBookId());
    }

    /**
     * Facade method for deleting AuthorWork objects from the AUTHOR_WORK table
     *
     * @param args
     */
    public void delete(AuthorWork aw) {
        performDelete(aw.getId());
    }

    public static void main(String[] args) {
        AuthorWorkDAO authorWork = new AuthorWorkDAO();
        BookDAO bookDao = new BookDAO();
        List<AuthorWork> aw = authorWork.queryAuthorWork();
        for (AuthorWork work : aw) {
            System.out.println(work.getAuthorId() + " - " + work.getBookId());
        }
        // May need to change the author_id value according to the values within
        // your database in order to make this work properly
        AuthorWork newAw = new AuthorWork(authorWork.getNextId(), 21, 102);
        authorWork.insert(newAw);
        aw = authorWork.queryAuthorWork();
        for (AuthorWork work : aw) {
            System.out.println(work.getAuthorId() + " - " + bookDao.performFind(work.getBookId()).getTitle());
        }
        System.out.println("-- Perform Update --");
        newAw.setBookId(103);
        authorWork.update(newAw);
        aw = authorWork.queryAuthorWork();
        for (AuthorWork work : aw) {
            System.out.println(work.getAuthorId() + " - " + bookDao.performFind(work.getBookId()).getTitle());
        }
        System.out.println("-- Perform Find --");
        aw = authorWork.performFind(102);
        for (AuthorWork work : aw) {
            System.out.println(work.getAuthorId() + " - " + bookDao.performFind(work.getBookId()));
        }
    }
}
