<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Password Management Tool</title>
</head>
<body>
	<h1>Welcome To Password Management Tool</h1>

	<form:form action="addAccountDetails" method="POST" modelAttribute="accountDto">
		<table>

			<tr>
				<td>Account Name</td>
				<td><form:input path="accountName"/><strong style="color: red"><form:errors path="accountName"></form:errors></strong></td>
			</tr>
			<tr>
				<td>Account UserName</td>
				<td><form:input path="accountUserName"/><strong style="color: red"><form:errors	path="accountUserName"></form:errors></strong></td>
			</tr>
			<tr>
				<td>Account Password</td>
				<td><form:input path="accountPassword"/><strong style="color: red"><form:errors path="accountPassword"></form:errors></strong></td>
			</tr>
			<tr>
				<td>Account URL</td>
				<td><form:input path="url" />
			</tr>
			
			<form:label path="category.categoryName">Group Name:</form:label>
            <form:select path="category.categoryId">
                <form:option value="0" label="--- Select ---"/>
                <form:options items="${category}" itemLabel="categoryName" itemValue="categoryId"  />
            </form:select>
            <form:errors path="category.categoryId" cssClass="error"/>
			
			
		       <tr>
				<td><input type="submit" value="Add Account" /></td>
				</tr>
				</table>
	</form:form>


</body>
</html>