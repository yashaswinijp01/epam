<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="ISO-8859-1">
				<link href="<c:url value=" /resources/bootstrap.css" />" rel="stylesheet">
				<link href="<c:url value=" /resources/style.css" />" rel="stylesheet">
				<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js" />
				<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
				<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
				<title>Password Management Tool</title>
			</head>

			<body>
				<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
					<div class="container">
						<a class="navbar-brand" href="#">Password Management Tool</a>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav ml-auto">

								<c:if test="${pageContext.request.userPrincipal.name != null}">
									<li class="nav-item active"><a class="nav-link"
											href="${pageContext.request.contextPath}/accounts/account">Add Account
										</a></li>

									<li class="nav-item active"><a class="nav-link"
											href="${pageContext.request.contextPath}/groups/group">Add Group</a>
									</li>

									<li class="nav-item active"><a class="nav-link"
											href="${pageContext.request.contextPath}/perform_logout">LogOut</a>
									</li>
								</c:if>
							</ul>
						</div>
					</div>
				</nav>

