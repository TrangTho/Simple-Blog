<%-- 
    Document   : header
    Created on : Jan 14, 2020, 9:53:25 AM
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
                    </div>
                    <div style="font-size: 18px;margin-left: 20px;">
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
