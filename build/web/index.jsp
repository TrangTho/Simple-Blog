<%-- 
    Document   : index
    Created on : Jan 7, 2020, 2:10:45 PM
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


        <div class="container" style="margin-top:30px">
            <h1 style="text-align: center;margin-top: 70px;">WELCOME ${sessionScope.NAME}</h1>
            <div class="text-center" style="margin-top:  20px;">
                <c:if  test="${sessionScope.ROLE eq 'admin'}">

                    <form action="MainController" method="POST">
                        <input type="hidden" name="txtPage" value="1"/>
                        <input  type="submit" name="action" value="ListOfAdminController" style="background-color: black; color: white;border: hidden;"/>
                    </form>
                </c:if> 

            </div>
            <div  class=" text-center" style="padding-top: 50px;padding-bottom: 50px;">
                <c:if test="${not empty sessionScope.TOP}">
                    <c:forEach var="dto" items="${sessionScope.TOP}" >
                        </br>
                        <h2>  
                            <c:url var="detailLink" value="MainController">
                                <c:param name="action" value="Detail"/>
                                <c:param name="id" value="${dto.id}"/>
                            </c:url>
                            <a href="${detailLink}"> ${dto.title} </a>
                        </h2>
                        </br>
                        <td>${dto.description}</td></br></br> 
                        <h6>Author: </h6>
                        <td>${dto.author}</td>
                        </br>
                    </c:forEach>
                </c:if>
            </div>
        </div>

        <div class=" text-center" style="margin-bottom:0;background: darkgrey;">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="footer-item">
                            <div class="footer-logo">
                                <a href="#"><img src="images/1.jpg" style="width: 100px;height: 100px;" alt=""></a>
                            </div>
                            <p>Dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                                dolore magna aliqua.</p>
                        </div>
                    </div>
                    <div class="col-lg-4">

                    </div>
                    <div class="col-lg-4">
                        <div class="footer-item">
                            <h5>Contact Info</h5>
                            <ul>
                                <li>1525 Boring Lane,<br />Los Angeles, CA</li>
                                <li>+1 (603)535-4592</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    </body>
</html>
