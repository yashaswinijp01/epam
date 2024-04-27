<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accounts based on Group</title>
</head>
<body>
	<form action="getAccountDetails" method="Get">
		<div align="center">

			<table>
				<tr>
					<td>Group Name</td>
					<td><input type="text" name="categoryName"></td>
				</tr>
				<tr>
				<td><input type="submit" value="GetAccounts"/>
				</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>