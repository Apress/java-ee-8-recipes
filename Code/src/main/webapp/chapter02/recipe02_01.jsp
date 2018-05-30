<%-- 
    Document   : recipe02_01
    Created on : Dec 23, 2011, 9:31:17 AM
    Author     : juneau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recipe 2-1: JSP Page Example</title>
    </head>
    <body>
        <jsp:useBean id="dateBean" scope="application" class="org.javaeerecipes.chapter02.recipe02_01.DateBean"/>
        <h1>Hello World!</h1>
        <br/>
        <p>
            The current date is: ${dateBean.currentDate}!
        </p>
    </body>
</html>
