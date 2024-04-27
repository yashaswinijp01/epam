
<%@include file="header.jsp"%>

				<center><h2>Login</h2></center>

			<div class="card-body">
				<c:if test="${param.error != null}">
					<div class="alert alert-danger" role="alert">Invalid Username / Password</div>
				</c:if>

				<form:form action="perform_login" method="post">
						<label for="exampleInputEmail1">Username</label>
						<input
							name="username" required type="text" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp">


						<label for="exampleInputPassword1">Password</label>
						<input
							required name="password" type="password" class="form-control"
							id="exampleInputPassword1">

					<button type="submit" class="btn btn-primary">Login</button>
				</form:form>

			<div class="card-footer">
				<div class="d-flex justify-content-center links">
					New User?<a
						href="${pageContext.request.contextPath}/register">Register</a>
				</div>
			</div>

		</div>
	</div>

</body>
</html>
