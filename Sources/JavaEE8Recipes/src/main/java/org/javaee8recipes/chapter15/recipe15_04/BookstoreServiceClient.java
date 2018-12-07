/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter15.recipe15_04;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceRef;
import org.javaee8recipes.chapter15.recipe15_01.endpoint.BookstoreService;
import org.javaee8recipes.chapter15.recipe15_01.endpointinterface.BookstoreEndpoint;

@WebServlet(name="BookstoreServiceServlet", urlPatterns={"/BookstoreServiceServlet"})
public class BookstoreServiceClient extends HttpServlet {
   
   
    /** 
     * Processes requests for both HTTP <code>GET</code> 
     *   and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<title>Servlet BookstoreServiceClient</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookstoreServlet at " + 
                request.getContextPath () + "</h1>");
            out.println("<p>" + obtainContacts() + "</p>");
            out.println("</body>");
            out.println("</html>");
            
        } finally { 
            out.close();
        }
    } 
    
    public void doGet(HttpServletRequest request, 
            HttpServletResponse response){
        try {
            processRequest(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(BookstoreServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BookstoreServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void doPost(HttpServletRequest request, 
            HttpServletResponse response){
        try {
            processRequest(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(BookstoreServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BookstoreServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String obtainContacts() {
        String contacts = "No Contacts";
        try {
            URL url = new URL("http://localhost:8080/JavaEERecipes/BookstoreServiceService?wsdl");

                QName qname = new QName("http://endpoint.recipe15_01.chapter15.javaeerecipes.org/", "BookstoreServiceService");
                Service service = Service.create(url, qname);
                BookstoreEndpoint bookstore = service.getPort(BookstoreEndpoint.class);

            contacts =  bookstore.obtainCompleteContactList();
        } catch (MalformedURLException ex) {
            Logger.getLogger(BookstoreServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contacts;
    }
}