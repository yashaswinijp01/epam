<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Add Account</title>
    </head>

    <body>
    <c:if test="${errorMessage != null}">
        <div class="alert alert-danger" role="alert">${errorMessage}</div>
    </c:if>
            <form:form action="addAccount" method="post"
               modelAttribute="accountBean">
               <table>
                   <tr>
                      <td>Account Name</td>
                     <td>
                            <form:input type="text" path="accountName" />
                            <b style="color: red"> <form:errors path="accountName"> </form:errors> </b>
                        </td>
                   </tr>
                  <tr>
                      <td>User Name</td>
                     <td>
                            <form:input type="test" path="userName" />
                            <b style="color: red"> <form:errors path="userName" /></b>
                        </td>
                   </tr>
                    <tr>
                      <td>Password</td>
                     <td>
                            <form:input type="password" path="password" />
                            <b style="color: red"> <form:errors path="password" /></b>
                        </td>
                  </tr>
                  <tr>
                      <td>Url</td>
                     <td>
                            <form:input type="url" path="url" />
                            <b style="color: red"> <form:errors path="url" /></b>
                        </td>
                  </tr>
                  <tr>
                        <div>
                            <label for="exampleInputPassword1">Group</label>
                            <form:select path="group.groupId" >
                                <form:option value = "0" label="None"/>
                                <form:options items="${groupDropDown}" itemValue="groupId" />
                            </form:select>
                        </div>
                    </tr>
                  <tr>
                      <td><input type="submit" value="Add Account" /></td>
                  </tr>
               </table>
            </form:form>




    </body>
</html>