<%-- 
    Document   : book
    Created on : Oct 19, 2017, 11:00:28 PM
    Author     : Juneau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">


        <title>Java EE 8 Recipes</title>

    </head>
    <body>

        <h1>Book List</h1>
        The book that was loaded in BookController:  ${bookContainer.book.title}
        <br/><br/>
        <div class="container">

            <h1>Book Listing</h1>

            <!-- In real world application this should go into a tag file -->
            <c:if test="${messages.info != null}">
                <div class="alert alert-success" role="alert">
                    ${messages.info}
                </div>
            </c:if>
            <c:if test="${not empty messages.errors}">
                <div class="alert alert-danger" role="alert">
                    <ul class="list-unstyled">
                        <c:forEach var="error" items="${messages.errors}">
                            <li>${error}</li>
                            </c:forEach>
                    </ul>
                </div>
            </c:if>
            <br/>
            <table class="table table-striped">
                <colgroup>
                    <col style="width: 80%;" />
                </colgroup>
                <thead>
                    <tr>
                        <th class="text-left">Book</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${bookContainer.bookList}">
                        <tr>

                            <td class="text-center">
                                ${book.title}
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <hr/>

            <h1>Create Book</h1>
            <form action="${pageContext.request.contextPath}/bookstore/book/create" method="POST" class="form-inline">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Book Information</h3>
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="subject">Title</label>
                            <input type="text" class="form-control" id="title" name="title" placeholder="Title"
                                   value="${book.title}" autofocus>
                        </div>

                    </div>
                </div>


                <br/><br/>
                <div class="form-group">
                    <label for="description">Description:</label>
                    <br/>
                    <textarea cols="100" rows="4" class="form-control" id="description" name="description" placeholder="Description">
                        ${book.description}
                    </textarea>
                </div>
                <br/><br/>
                <button type="submit" class="btn btn-primary">Create</button>
            </form>
            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    </body>
</html>
