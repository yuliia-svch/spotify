<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
crossorigin="anonymous">
<html>
<body onload='document.loginForm.username.focus();'>
<div class="container p-6 my-3 h-50 w-25 border rounded">
  <div class="row align-items-center h-100">
    <div class="col-10 mx-auto">
	<h3>Login to Spotify</h3>
    <br/>
	<c:if test="${not empty error}"><div style="color:red;">${error}</div></c:if>
	<c:if test="${not empty message}"><div style="color:green;">${message}</div></c:if>

	<form name='login' action="<c:url value='/loginPage' />" method='POST'>
		<table>
			<tr>
				<td><p>UserName:</p></td>
				<td><input type='text' name='username' class="form-control" value=''></td>
			</tr>
			<tr>
				<td><p>Password:</p></td>
				<td><input type='password' name='password' class="form-control"/></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit" class="btn btn-success" value="Submit" /></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<br/>
	<a href="${contextPath}/registerPage">Create an account</a>
	</div>
	</div>
	</div>
</body>
</html>