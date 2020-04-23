<%-- 
    Document   : listBlog
    Created on : Jan 9, 2020, 9:59:40 AM
    Author     : trang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Simple Blog</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/style.css" type="text/css">

    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light navF" id="ftco-navbar" style="z-index: 1;">
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
                        <c:if  test="${sessionScope.ROLE ==null}">
                            <form action="MainController" method="POST">
                                <input type="hidden" name="txtPage" value="1"/>
                                <input  type="submit" name="action" value="LOGIN" style="background-color: black; color: white;border: hidden;"/>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
        </nav>
        <div class="container">
            <form action="MainController" style="position: relative;" method="POST">
                <input style="width: 80%;margin-top: 70px;" class="form-control" type="text" name="txtSearchValue" value="${param.txtSearchValue}" /><br/>
                <input type="hidden" name="txtPage" value="1"/>
                <input style="position: absolute; top: 3px; right: 70px; width: 100px;" class="btn btn-primary" type="submit" value="Search" name="action" /><br/>
            </form>
        </div>
        <div class="container">

            <h1 style="text-align: center;margin-top: 10px;">WELCOME ${sessionScope.NAME}</h1>


            <c:set var="searchValue" value="${param.txtSearchValue}"/>
            <c:if test="${not empty searchValue}">
                <c:set var="loadPage" value="${requestScope.LOADPAGE}"/>
                <c:if test="${not empty loadPage.listActicle}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Title</th>
                                <th>Short description</th>
                                <th>Author</th>
                                <th>Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${loadPage.listActicle}" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td><c:url var="detailLink" value="MainController">
                                            <c:param name="action" value="Detail"/>
                                            <c:param name="id" value="${dto.id}"/>
                                        </c:url>
                                        <a href="${detailLink}"> ${dto.title} </a>
                                    </td>
                                    <td>${dto.description}</td>
                                    <td>${dto.author}</td>
                                    <td>${dto.date}</td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div style="text-align: center;">
                        <c:forEach var="i" begin="1" end="${loadPage.totalPage}" step="1">
                            <a href="MainController?action=Search&txtSearchValue=${searchValue}&txtPage=${i}">${i}</a>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${empty loadPage.listActicle}">
                    <h1>${requestScope.NLIST}</h1>
                </c:if>

            </c:if>
            <c:if test="${empty searchValue}">
                <h1>${requestScope.INVALID}</h1>
            </c:if>



            <c:set var="numberPage" value="${requestScope.NUMBERPAGE}"/>
            <c:if test="${not empty numberPage.listActicle}">
    <!--            <h2>${numberPage.totalPage}</h2>-->
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Author</th>
                            <th>Posting Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${numberPage.listActicle}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    <c:url var="detailLink" value="MainController">
                                        <c:param name="action" value="Detail"/>
                                        <c:param name="id" value="${dto.id}"/>
                                    </c:url>
                                    <a href="${detailLink}"> ${dto.title} </a>
                                </td>
                                <td>${dto.description}</td>
                                <td>${dto.author}</td>
                                <td>${dto.date}</td>

                            </tr>
                        </c:forEach>


                    </tbody>
                </table>
                <div style="text-align: center;">
                    <c:forEach var="i" begin="1" end="${numberPage.totalPage}" step="1">
                        <a href="MainController?action=List Blog&txtPage=${i}">${i}</a>
                    </c:forEach>
                </div>
                <c:if test="${empty numberPage.listActicle}">
                    <h1>${requestScope.INVALID}</h1>
                </c:if>
            </c:if>

        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
