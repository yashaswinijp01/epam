<%@include file="header.jsp"%>

				<h2>${head}</h2>

				<c:if test="${errorMessage != null}">
					<div class="alert alert-danger" role="alert">${errorMessage}</div>
				</c:if>
				<form:form modelAttribute="groupBean" method="post">
					<div class="form-group">
						<label for="exampleInputEmail1">Group Name</label> 
						<input type="text" name="groupName" class="form-control" required placeholder="Group name" value="${groupBean.groupName}">
						<form:errors cssClass="has-error" path="groupName" />
					</div>
			
					<button type="submit" class="btn btn-primary">${head}</button>
				</form:form>

</body>
</html>