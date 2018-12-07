<%-- 
    Document   : bookAuthor
    Created on : Oct 18, 2017, 12:03:40 PM
    Author     : Juneau
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Example of MVC Using JSP for View</title>
    </head>
    <body>
        <h1>Book Authors</h1>
        <table class="table table-striped">
                <colgroup>
                    <col style="width: 80%;" />
                </colgroup>
                <thead>
                    <tr>
                        <th class="text-left">Author</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="bookAuthor" items="${bookauthors}">
                        <tr>

                            <td class="text-center">
                                ${bookAuthor.last}
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
    </body>
</html>
