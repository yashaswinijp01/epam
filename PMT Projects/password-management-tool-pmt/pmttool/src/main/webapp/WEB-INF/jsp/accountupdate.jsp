<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Enter Details to be updated</h1>
<form:form action="editAccountDetails" method="post" modelAttribute="accountDto">
		<table>
		<tr>
			<td>Account ID</td>
				<td><input type="text" name="id"></td>
				</tr>
			<tr>
			<td>Account Name</td>
				<td><input type="text" name="accountName"><strong style="color: red"><form:errors
							path="accountName"></form:errors></strong></td>
				</tr>
		   <tr>
				<td>Account UserName</td>
				<td><input type="text" name="accountUserName"><strong style="color: red"><form:errors
							path="accountUserName"></form:errors></strong></td>
			</tr>
			<tr>
				<td>Account Password</td>
				<td><input type="text" name="accountPassword"><strong style="color: red"><form:errors
							path="accountPassword"></form:errors></strong></td>
			</tr>
			
			<tr>
			<td>Account URL</td>
			<td><input type="text" name="url"></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="submit" /></td>
			</tr>
			</table>
			</form:form>
</body>
</html>