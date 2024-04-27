<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adding Group</title>

</head>
<body>

<form:form action="addCategoryDetails" method="post" modelAttribute="categoryDto">
<table>
		
			<tr>
				<td>Group Name</td>
				<td><input type="text" name="categoryName"><b style="color:red"><form:errors path="categoryName"></form:errors></b></td>
			</tr>
			<tr>				
				<td><input type="submit" value="AddGroup"/></td>
			</tr>
			
</table>
</form:form>

</body>
</html>