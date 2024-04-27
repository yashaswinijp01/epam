<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<TABLE BORDER="1">
      <TR>
      <TH>ID</TH>
      <TH>Category Name</TH>
      <TH> Update Category</TH>
      <TH> Delete Category</TH>
      </TR>
      <c:forEach items="${categories}" var="accounts">
     <TR>
       <td> ${accounts.categoryId}</td>
       <td>  ${accounts.categoryName}</td>
       <td><a href="editCategory">Update</a></td>
       <td><a href="deletecategory?categoryId=${accounts.categoryId}"> Delete </a>   
      </TR>
      </c:forEach>
     </TABLE>
    </div>
<br>
<div align="center"><a href="addCategory" class="GFG">
        AddCategory
    </a></div>
<br>
<br>
<br>
<div align="center"><a href="categoryWelcome">Home</a></div>
</body>
</html>