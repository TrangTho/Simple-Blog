<%-- 
    Document   : detailAmin
    Created on : Jan 14, 2020, 1:27:06 PM
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
        <div>
            <c:if test="${requestScope.INFO != null}">
                <c:if test="${not empty requestScope.INFO}" var="checkList">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Title</th>
                                <th>Description</th>
                                <th>Content</th>
                                <th>Author</th>
                                <th>Posting Date</th>
                                <th>Status </th>
                                <th>Function </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.INFO}" var="dto" varStatus="counter"> 
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${dto.title}</td>
                                    <td>${dto.description}</td>
                                    <td>${dto.content}</td>
                                    <td>${dto.author}</td>
                                    <td>${dto.date}</td>
                                    <td>${dto.status}</td>
                                    <td>
                                        <c:if test="${dto.status eq 'Active'}">
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="id" value="${dto.id}"/>
                                                <input style="width: 60px;height: 30px; font-size:10px;" type="submit" name="action" value="DELETE"/>
                                            </form>
                                        </c:if>

                                        <c:if test="${dto.status eq 'New'}">
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="id" value="${dto.id}"/>
                                                <input style="width: 60px;height: 30px; font-size:10px;"  type="submit" name="action" value="APPROVAL"/>
                                            </form>
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="id" value="${dto.id}"/>
                                                <input style="width: 60px;height: 30px; font-size:10px;" type="submit" name="action" value="DELETE"/>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </c:if>
                <c:if test="${!checkList}">
                    No content article!
                </c:if>
            </c:if>

        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
