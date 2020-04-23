<h1>Hello ${sessionScope.NAME}!</h1>
<div class="container"style="margin-top: 70px;margin-bottom: 50px;">
    <form action="MainController" method="POST">
        Title:  <input class="form-control" type="text" name="txtTitle" value="${param.txtTitle}" /> 
        <br/>
        <c:if test="${requestScope.INVALID.titleError != null}">
            <font color="red">
            ${requestScope.INVALID.titleError}
            </font>
        </c:if> 


        <br/><br/>

        Short Description:
        <textarea class="form-control" rows="10" cols="150" name="txtDescription"></textarea>
        <br/><br/>
        <c:if test="${requestScope.INVALID.descriptionError != null}">
            <font color="red">
            ${requestScope.INVALID.descriptionError}
            </font>
        </c:if> 
        <br/><br/>

        Content:
        <textarea class="form-control" rows="10" cols="150"  name="txtContent"></textarea>
        <br/><br/>
        <c:if test="${requestScope.INVALID.contentError != null}">
            <font color="red">
            ${requestScope.INVALID.contentError}
            </font>
        </c:if> 
        <br/>
        <input type="hidden" name="txtAuthor" value="${sessionScope.EMAIL}"/>
        <input class="btn btn-primary" type="submit" name="action" value="POST" />

    </form>
</div>
