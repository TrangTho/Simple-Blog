<%-- 
    Document   : admin
    Created on : Jan 8, 2020, 8:41:00 PM
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
        <c:if test="${sessionScope.ROLE ne 'admin'}">
            <jsp:forward page="index.jsp" />
        </c:if>
        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
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
                        <c:if  test="${sessionScope.ROLE eq 'admin'}">
                            <form action="MainController" method="POST">
                                <input  type="submit" name="action"  value="LOG OUT" style="background-color: black; color: white;border: hidden;"/> 
                            </form>
                        </c:if> 
                    </div>
                    <div style="font-size: 18px;margin-left: 20px;">
                        <form action="MainController" method="POST">
                            <c:if  test="${sessionScope.ROLE ==  null}">
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
            <div class="text-center" >
                <form action="MainController" method="POST">
                    <input type="hidden" name="txtPage" value="1"/>
                    <input type="submit" name="action" value="Search By Content"/>
                </form>
                <form action="MainController" method="POST">
                    <input type="hidden" name="txtPage" value="1"/>
                    <input type="submit" name="action" value="Search By Title and Status" />
                </form>
                <form action="MainController" method="POST">
                    <input type="text" name="txtEmail" />
                    <input type="submit" name="action" value="DeleteUser" />
                </form>
            </div>
            <div class="row" style="padding-top: 50px;padding-bottom: 50px;">

                <div class="col">
                    <c:set var="numberPage" value="${requestScope.LOADARTICLEADMIN}"/>
                    <c:if test="${not empty numberPage.listActicle}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Title</th>
                                    <th>Description</th>
                                    <th>Author</th>
                                    <th>Posting Date</th>
                                    <th>Status</th>
                                    <th>Function</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${numberPage.listActicle}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>
                                            <c:url var="detailLink" value="MainController">
                                                <c:param name="action" value="DetailAdmin"/>
                                                <c:param name="id" value="${dto.id}"/>
                                            </c:url>
                                            <a href="${detailLink}"> ${dto.title} </a>
                                        </td>
                                        <td>${dto.description}</td>
                                        <td>${dto.author}</td>
                                        <td>${dto.date}</td>
                                        <td>${dto.status}</td>
                                        <td >
                                            <c:if test="${dto.status eq 'Active'}">
                                                <form action="MainController" method="POST">
                                                    <input type="hidden" name="id" value="${dto.id}"/>
                                                    <input style="width: 60px;height: 30px; font-size:10px; "  type="submit" name="action" value="DELETE"/>
                                                </form>
                                            </c:if>

                                            <c:if test="${dto.status eq 'New'}">
                                                <form action="MainController" method="POST">
                                                    <input type="hidden" name="id" value="${dto.id}"/>
                                                    <input style="width: 60px;height: 30px; font-size:10px;"  type="submit" name="action" value="APPROVAL"/>
                                                </form>
                                                <form action="MainController" method="POST">
                                                    <input type="hidden" name="id" value="${dto.id}"/>
                                                    <input style="width: 60px;height: 30px; font-size:10px;"  type="submit" name="action" value="DELETE"/>
                                                </form>
                                            </c:if>

                                        </td>
                                    </tr>
                                </c:forEach>


                            </tbody>
                        </table>
                        <div class="center" style="margin-top: 10px;">
                            <div class="test" >
                                <c:forEach var="i" begin="1" end="${numberPage.totalPage}" step="1">
                                    <a href="MainController?action=ListOfAdminController&txtPage=${i}">${i}</a>
                                </c:forEach>

                            </div>
                        </div>



                    </c:if>
                    <c:if test="${empty numberPage.listActicle}">
                        <h1>No Record</h1>
                    </c:if>
                </div>
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
