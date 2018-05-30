<%-- 
    Document   : recipe02_03
    Author     : juneau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recipe 2-3: Separating Business Logic from View Code</title>
    </head>
    <body>
        <jsp:useBean id="randomBean" scope="session" class="org.javaeerecipes.chapter02.recipe02_03.RandomBean"  />
        <h1>Display a Random Number</h1>
        <br/>
        <br/>
        <p>
            Your random number is ${randomBean.randomNumber}.  Refresh page to see another!  
        </p>
    </body>
</html>
