<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Group Welcome</title>
</head>
<body>
<div align="center">
<h1><b style="color: green">Hey ${users.userName} Welcome !!!</b></h1>
</div>
<div align="center">
 
 <h1> <a href="addCategory">AddCategory</a></h1>
  <h1><a href="viewcategories">ViewCategories</a></h1>
   <h1> <a href="viewaccounts">ViewAccounts</a></h1>
  <h1> <a href="addAccount">AddAccount</a></h1>
 <h1><a href="getAccounts">GetAccountsByCategory</a></h1>
  <h1><a href="logout">LogOut</a></h1>
  </div>

</body>
</html>