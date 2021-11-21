<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
crossorigin="anonymous">

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Create an account</title>

  </head>

  <body>

    <div class="container p-6 my-3 h-50 w-25 border rounded">
      <div class="row align-items-center h-100">
        <div class="col-10 mx-auto">
        <c:if test="${not empty message}"><div>${message}</div></c:if>
        <form:form method="POST" modelAttribute="user" class="form-signin">
            <h2 class="form-signin-heading">Create your account</h2>
            <br/>
            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                autofocus="true"></form:input>

                    <form:errors path="username" style="color:red;"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                    <form:errors path="password" style="color:red;"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="matchingPassword">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="matchingPassword" class="form-control"
                                placeholder="Confirm your password"></form:input>
                    <form:errors path="matchingPassword" style="color:red;"></form:errors>
                </div>
            </spring:bind>
            <br/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>
        <a href="${pageContext.request.contextPath}/loginPage">Login</a>
    </div>
</div>
</div>
  </body>
</html>