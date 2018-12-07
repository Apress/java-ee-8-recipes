
package org.javaee8recipes.chapter01.recipe01_13;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Recipe 1-13
 * 
 * @author juneau
 */
// Uncomment the following line to run example stand-alone
//@WebServlet(name = "DownloadServlet", urlPatterns = {"/DownloadServlet"})

// The following will allow the example to run within the context of the JavaEERecipes example
// enterprise application (javaee8recipes.war distro or Netbeans Project
@WebServlet(name = "DownloadServlet", urlPatterns = {"/chapter01/DownloadServlet"})
public class DownloadServlet extends HttpServlet {

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Read parameter from form that contains the filename to download
        String fileToDownload = request.getParameter("filename");
        // Call the download method with the given file
        System.err.println("Downloading file now...");
        doDownload(request, response, fileToDownload);
    }

    
    /**
     *  Sends a file to the output stream.  
     *
     *  @param req The request
     *  @param resp The response
     *  @param original_filename The name the browser should receive.
     */
    private void doDownload( HttpServletRequest request, HttpServletResponse response,
                             String originalFile) throws IOException {
        final int BYTES = 1024;
        int                 length   = 0;
        ServletOutputStream outStream = response.getOutputStream();
        ServletContext      context  = getServletConfig().getServletContext();


        response.setContentType( (context.getMimeType( originalFile ) != null) ?
                context.getMimeType( originalFile ) : "application/octet-stream" );
        response.setHeader( "Content-Disposition", "attachment; filename=\"" + originalFile + "\"" );

        InputStream in = context.getResourceAsStream("/" + originalFile);
        byte[] bbuf = new byte[BYTES];
       
        while ((in != null) && ((length = in.read(bbuf)) != -1))
        {
            outStream.write(bbuf,0,length);
        }

       // outStream.flush();
        outStream.close();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
