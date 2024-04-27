<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Enter Details to be updated</h1>
<form action="editCategoryDetails" method="post" >
		<table>
		<tr>
				<td>Group Id</td>
				<td><input type="text" name="categoryId"></td>
			</tr>
		<tr>
				<td> Group Name</td>
				<td><input type="text" name="categoryName"></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="update" /></td>
			</tr>
			</table>
			</form>
</body>
</html>