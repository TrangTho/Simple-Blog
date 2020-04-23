<%-- 
    Document   : active.jsp
    Created on : Jan 19, 2020, 8:13:08 AM
    Author     : trang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Check mail to verification account</h3>
        <form action="MainController" method="POST">
            <input type="text" name="code" value="${param.code}"/>
            <input type="hidden" name="email" value="${requestScope.EMAIL}"/>
            <input type="submit" name="action" value="Sent"/>
        </form>
    </body>
</html>
