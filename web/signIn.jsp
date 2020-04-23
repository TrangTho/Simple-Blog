<%-- 
    Document   : signIn
    Created on : Jan 8, 2020, 7:11:52 PM
    Author     : trang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple Blog</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

    </head>
    <body>

        <form action="MainController" method="POST">
            Email: <input class="form-control" type="text" name="txtEmail" value="${param.txtEmail}"/> 
            <c:if test="${requestScope.INVALID.emailError != null}">
                <font color="red">
                ${requestScope.INVALID.emailError}
                </font>
            </c:if> 


            <br/>
            Password: <input class="form-control" type="password" name="txtPassword"/> 
            <c:if test="${requestScope.INVALID.emailError != null}">
                <font color="red">
                ${requestScope.INVALID.passwordError}
                </font>
            </c:if> 
            <br/>
            <input type="hidden" name="txtPage" value="1" />
            <input class="btn btn-primary" type="submit" name="action" value="Login" />
        </form>
        <a href="signUp.jsp">Register Account </a>
        
    </body>
</html>
