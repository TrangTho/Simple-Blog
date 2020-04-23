<%-- 
    Document   : signUp
    Created on : Jan 8, 2020, 9:14:22 AM
    Author     : trang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple Blog</title>
    </head>
    <link href='https://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="./css/editProfile.css">
    <body>
        <form action="MainController" method="POST">

            <h1>Sign Up</h1>

            <fieldset>
                <legend><span class="number">1</span>INFORMATION</legend>

                <label for="name">Email: </label>
                <input type="text" name="txtEmail" value="${param.txtEmail}"placeholder="Phone" />
                <font color="red">
                ${requestScope.INVALID.emailError}
                </font>
                <br/>
                <label for="name"> Name:</label>
                <input type="text" name="txtName" placeholder="User" />
                <font color="red">
                ${requestScope.INVALID.nameError}
                </font>
                <br/>
                <label for="password">Password:</label>
                <input type="password" name="txtPassword"placeholder="Password" />
                <font color="red">
                ${requestScope.INVALID.passwordError}
                </font>
                <br/>
                <label for="password">Confirm Password:</label>
                <input type="password" name="txtConfirm" placeholder="Confirm Password" />
                <font color="red">
                ${requestScope.INVALID.confirmError}
                </font>
                <br/>

            </fieldset>


        </fieldset>
        <input class="formInput" type="submit" name="action" value="Register"></button>
    </form>
</body>
</html>
