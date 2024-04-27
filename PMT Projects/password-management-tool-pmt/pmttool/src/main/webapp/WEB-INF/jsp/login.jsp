<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Login Form</title>
</head>
<body>
	<h1 align="center">Login Form</h1>
	<form:form action="loginDetails" method="POST" modelAttribute="userDto">

		<div align="center"class="mb-3">
			<label for="exampleInputEmail1" class="form-label">UserName</label> 
			<form:input path="userName" /><b style="color: red"><form:errors path="userName"></form:errors></b>
		</div>

		<div align="center" class="mb-3">
			<label for="exampleInputPassword1" class="form-label">Password</label>
			<form:input path="password" /><b style="color: red"><form:errors
						path="password"></form:errors></b>
			
		</div>


	<div align="center">	
  <button type="submit" class="btn btn-primary">login</button>
  </div>

		
	</form:form>


</body>
</html>