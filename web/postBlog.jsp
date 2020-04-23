<%-- 
    Document   : postBlog
    Created on : Jan 8, 2020, 7:39:24 PM
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
        <c:if test="${sessionScope.ROLE ne 'member'}">
            <jsp:forward page="signIn.jsp" />
        </c:if>
       <%@include file="header.jsp" %>
        <%@include file="postBody.jsp" %>
        <%@include file="footer.jsp" %>
    </body>
</html>
