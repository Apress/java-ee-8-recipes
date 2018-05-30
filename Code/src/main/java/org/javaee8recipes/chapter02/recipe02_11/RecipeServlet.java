
package org.javaee8recipes.chapter02.recipe02_11;

import org.javaee8recipes.common.CreateConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Recipe 2-11:  Creating an Input Form
 * @author juneau
 */
@WebServlet(name = "RecipeServlet", urlPatterns = {"/RecipeServlet"})
public class RecipeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        int result = -1;
        try(PrintWriter out = response.getWriter()) {
            /*
             * TODO Perform validation on the request parameters here
             */
            result = insertRow (request.getParameter("recipeNumber"),
                       request.getParameter("name"),
                       request.getParameter("description"),
                       request.getParameter("text"));
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RecipeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RecipeServlet at " + request.getContextPath() + "</h1>");
            out.println("<br/><br/>");
            
            if(result > 0){
                out.println("<font color='green'>Record successfully inserted!</font>");
                out.println("<br/><br/><a href='/JavaEERecipes/chapter02/recipe02_11.jspx'>Insert another record</a>");
            } else {
                out.println("<font color='red'>Record NOT inserted!</font>");
                out.println("<br/><br/><a href='/JavaEERecipes/chapter02/recipe02_11.jspx'>Try Again</a>");
            }

            out.println("</body>");
            out.println("</html>");
        } 
    }
    
    public int insertRow(String recipeNumber,
                          String name,
                          String description,
                          String text) {
       
        String sql = "INSERT INTO RECIPES VALUES(" +
                     "RECIPES_SEQ.NEXTVAL,?,?,?,?)";
       
        int result = -1;
        CreateConnection createConn = new CreateConnection();
        try(Connection conn = createConn.getConnection();
            PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql)) {
            
            
            stmt.setString(1, recipeNumber);
            stmt.setString(2, name);
            stmt.setString(3, description);
            stmt.setString(4, text);
            // Returns row-count or 0 if not successful
            result = stmt.executeUpdate();
            if (result > 0){
                System.out.println("-- Record created --");
            } else {
                System.out.println("!! Record NOT Created !!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return result;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}
