
package org.javaee8recipes.chapter01.recipe01_14;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author juneau
 */
// Uncomment the following line to run example stand-alone
//@WebServlet(name = "MathDispatcher", urlPatterns = {"/MathDispatcher"})

// The following will allow the example to run within the context of the JavaEERecipes example
// enterprise application (javaee8recipes.war distro or Netbeans Project
@WebServlet(name = "MathDispatcher", urlPatterns = {"/chapter01/MathDispatcher"})
public class MathDispatcher extends HttpServlet {

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         System.out.println("In the servlet...");
        // Store the input parameter values into Strings
                String eval = request.getParameter("matheval");
                ServletContext sc = getServletConfig().getServletContext();
                RequestDispatcher rd = null;
                int evaluate = 0;
                int add = 0;
                int subtract = 1;
                int multiply = 2;
                int divide = 3;
                if(eval.equals("add"))
                    evaluate = add;
                if (eval.equals("subtract"))
                    evaluate = subtract;
                if (eval.equals("multiply"))
                    evaluate = multiply;
                if(eval.equals("divide")){
                    evaluate = divide;
                }
                /*
                 switch(evaluate){
                    case(0): rd =  sc.getRequestDispatcher("/AddServlet");
                                 rd.forward(request, response);
                                 break;
                    case(1): rd =  sc.getRequestDispatcher("/SubtractServlet");
                                      rd.forward(request, response);
                                      break;
                    case(2): rd =  sc.getRequestDispatcher("/MultiplyServlet");
                                      rd.forward(request, response);
                                      break;
                    case(3): rd =  sc.getRequestDispatcher("/DivideServlet");
                                    rd.forward(request, response);
                                    break;
                } 
                 */
                switch(evaluate){
                    case(0): rd =  sc.getRequestDispatcher("/chapter01/AddServlet");
                                 rd.forward(request, response);
                                 break;
                    case(1): rd =  sc.getRequestDispatcher("/chapter01/SubtractServlet");
                                      rd.forward(request, response);
                                      break;
                    case(2): rd =  sc.getRequestDispatcher("/chapter01/MultiplyServlet");
                                      rd.forward(request, response);
                                      break;
                    case(3): rd =  sc.getRequestDispatcher("/chapter01/DivideServlet");
                                    rd.forward(request, response);
                                    break;
                }
                        
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
