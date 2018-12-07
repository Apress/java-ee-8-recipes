
package org.javaee8recipes.chapter02.recipe02_12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.javaee8recipes.common.CreateConnection;
import org.javaee8recipes.entity.BookAuthor;

/**
 * Recipe 2-12
 * @author juneau
 */
public class AuthorBean implements java.io.Serializable {
    
    public static Connection conn = null;
    private List<Author> authorList = null;
    
    
    public AuthorBean(){
        
    }

    public List queryAuthors(){
        String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM BOOK_AUTHOR";
        List <Author> authorList = new ArrayList<Author>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int result = -1;
        try {
            CreateConnection createConn = new CreateConnection();
            conn = createConn.getConnection();
            stmt = (PreparedStatement) conn.prepareStatement(sql);
            
            // Returns row-count or 0 if not successful
             rs = stmt.executeQuery();
             System.out.println("executing statement");
             while (rs.next()){
                 Author author = new Author();
                 author.setId(rs.getInt("ID"));
                 author.setFirst((rs.getString("FIRSTNAME")));
                 author.setLast(rs.getString("LASTNAME"));
                 authorList.add(author);
                 System.out.println("got author: " + author);
             }
             
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            
        }
        return authorList;
    }
    
    public List getAuthorList(){
        authorList = queryAuthors();
        System.out.println("authorlist: " + authorList);
        return authorList;
    }
    

}
