/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee8recipes.chapter17;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Recipe 17-04
 *
 */
@WebServlet({"/login"})
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write(
            "<html><body> Login to continue \n" +
                "<form method=\"POST\" action=\"j_security_check\">" +
                    "<p><strong>Username </strong>" +
                    "<input type=\"text\" name=\"j_username\">" +
                    
                    "<p><strong>Password </strong>" +
                    "<input type=\"password\" name=\"j_password\">" +
                    "<p>" +
                    "<input type=\"submit\" value=\"Submit\">" +
                "</form>" +
            "</body></html>");
    }

}
