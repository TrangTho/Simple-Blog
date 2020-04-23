<%-- 
    Document   : detailBlog
    Created on : Jan 9, 2020, 8:55:49 AM
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
        <link rel="stylesheet" href="./css/style.css" type="text/css">

    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light navF" id="ftco-navbar">
            <div class="container">
                <a class="navbar-brand" href="index.jsp">BLOG</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav">
                    Menu
                </button>

                <div class="collapse navbar-collapse"  id="ftco-nav">
                    <div class="b" >
                        <a class="nav-link " class="b" href="index.jsp">Home</a>
                    </div>
                    <div class="b">
                        <form action="MainController" method="POST" >
                            <input type="hidden" name="txtPage" value="1" />

                            <input  type="submit" name="action" value="List Blog" style="background-color: black; color: white;border: hidden;"/>
                        </form> 
                    </div>
                    <div class="b">
                        <form action="MainController" method="POST">
                            <input  type="submit" name="action" value="CREATE POST" style="background-color: black; color: white;border: hidden;"/>
                        </form>
                    </div>

                    <div class="b">
                        <c:if  test="${sessionScope.ROLE eq 'member'}">
                            <form action="MainController" method="POST">
                                <input  type="submit" name="action"  value="LOG OUT" style="background-color: black; color: white;border: hidden;"/> 
                            </form>
                        </c:if> 
                        <c:if  test="${sessionScope.ROLE eq 'admin'}">
                            <form action="MainController" method="POST">
                                <input  type="submit" name="action"  value="LOG OUT" style="background-color: black; color: white;border: hidden;"/> 
                            </form>
                        </c:if> 
                    </div>
                    <div style="margin-left: 20px;">
                        <c:if  test="${sessionScope.ROLE == null}">
                            <form action="MainController" method="POST">
                                <input type="hidden" name="txtPage" value="1"/>
                                <input  type="submit" name="action" value="LOGIN" style="background-color: black; color: white;border: hidden;"/>
                            </form>
                        </c:if> 
                    </div>
                </div>
            </div>
        </nav>

        <div>
            <h1 style="text-align: center;margin-top: 100px;">WELCOME ${sessionScope.NAME}</h1>
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
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </c:if>
                <c:if test="${!checkList}">
                    No content article!
                </c:if>
            </c:if>

            <h2>COMMENT</h2>

        </div>
        <div>
            <c:if test="${requestScope.INVALID.contentError != null}">
                <font color="red">
                ${requestScope.INVALID.contentError}
                </font>
            </c:if> 
            <form action="MainController" method="POST">
                Comment:   <input class="form-control" type="text" name="txtComment" value="${param.txtComment}" /> 

                <input type="hidden" name="id" value="${param.id}"/>
                <input type="submit" name="action" value="Send" />

            </form>
            <c:if test="${requestScope.COMMENT != null}">
                <c:if test="${not empty requestScope.COMMENT}" var="checkList">
                    <table border="0">

                        <tbody>
                            <c:forEach items="${requestScope.COMMENT}" var="dto" > 
                                <tr>
                                    <td>${dto.user} :         </td>
                                    <td>${dto.content}</td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </c:if>
                <c:if test="${!checkList}">
                    No Comment!
                </c:if>
            </c:if>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
