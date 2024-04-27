<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accounts</title>
</head>
<body>
<div align="center">
<TABLE BORDER="1">
      <TR>
      <TH>ID</TH>
      <TH>AccountName</TH>
      <TH>Account UserName</TH>
      <TH>Account Password</TH>
      <TH>URL</TH>
      <TH>Category Name</TH>  
      <TH> Update Account</TH>
      <TH> Delete Account </TH>    
      </TR>
      <c:forEach items="${accounts}" var="account">
     <TR>
       <td> ${account.id}</td>
       <td>  ${account.accountName}</td>
       <td> ${account.accountUserName}</td>
       <td> ${account.accountPassword}</td>
       <td> ${account.url}</td>
       <td>  ${account.category.categoryName}</td> 
       <td><a href="editaccount?id=${account.id}"> Update </a> 
       <td><a href="deleteaccount?id=${account.id}"> Delete </a>     
      </TR>
      </c:forEach>
     </TABLE>
 </div>    
<br>
<br>
<div align="center"><a href="addAccount">AddAccount</a></div>
<br>
<br>
<br>
 <div align="center"><a href="categoryWelcome">Home</a> </div>
</body>
</html>