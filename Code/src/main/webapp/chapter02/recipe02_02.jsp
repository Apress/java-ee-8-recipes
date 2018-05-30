<%-- 
    Document   : recipe02_02
    Author     : juneau
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%! Date currDate = null; %>
<% currDate = new Date(); %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recipe 2-2: Embedding Java in a JSP</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <br/>
        <br/>
        The current date and time is: <%= currDate %>
        
    </body>
</html>
