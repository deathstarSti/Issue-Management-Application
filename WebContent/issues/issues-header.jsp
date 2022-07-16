

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>

<head>
<title>Issue Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
	
	 <script>
				

				function getDeleteConfirmation() {
					var retVal = confirm("Are you sure you want to Delete User?");
					if( retVal == true ) {
						
						return true;
					} else {
						
						return false;
					}
            	}
			</script>
</head>

<body>

	<header>




		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="<%=request.getContextPath()%>" class="navbar-brand">
					Issues Management App </a>
			</div>
			<ul class="navbar-nav mr-auto">
				<li class="nav-item dropdown active"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> Issues </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/issues/" class="nav-link">All Issues</a> 
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/issues/bug" class="nav-link">Bug Issues</a>
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/issues/feature" class="nav-link">Feature Issues</a>	
							
					</div>
				</li>
			</ul>


			<ul class="navbar-nav navbar-right">
			<li class="nav-item dropdown active"><a class="nav-link dropdown-toggle" href="#"
					id="userDropdownMenuLink" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> <span style="color:#883333;font-size:110%"><c:out value="${user.username}" /></span> </a>
					<div class="dropdown-menu" aria-labelledby="userDropdownMenuLink">
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/edit-user?id=<c:out value='${user.id}' />" class="nav-link">Edit Account</a> 
						<a class="dropdown-item"
							href="<%=request.getContextPath()%>/delete-user?id=<c:out value='${user.id}' />" class="nav-link" onclick="return getDeleteConfirmation()">Delete Account</a> 
						
							
					</div>
				</li>
				
			</ul>
			<ul class="navbar-nav navbar-right">
				<li><a href="<%=request.getContextPath()%>" class="nav-link">Sign
						out</a></li>
			</ul>

		</nav>


	</header>
	<br>