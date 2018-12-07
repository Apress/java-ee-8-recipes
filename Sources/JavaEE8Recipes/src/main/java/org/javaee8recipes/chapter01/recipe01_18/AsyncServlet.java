package org.javaee8recipes.chapter01.recipe01_18;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import javax.servlet.AsyncContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "AsyncServlet", urlPatterns = {"/AsyncServlet"}, asyncSupported = true)
//@WebFilter(urlPatterns="/*", asyncSupported=true)
public class AsyncServlet extends HttpServlet implements Filter {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            System.out.println("Starting doGet");
            AsyncContext ac = request.startAsync();
            ac.addListener(new MyListener());
            System.out.println("Do some stuff in doGet ...");
// Start another service
            ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
            executor.execute(new ServletService(ac));
            System.out.println("Some more stuff in doGet ...");
        } finally {
        }
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        System.out.println("Going through the servlet filter...");
    }
}
