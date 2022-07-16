<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Issue Management App</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">



</head>

<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="<%=request.getContextPath()%>" class="navbar-brand">
					Issue Management App </a>
			</div>


		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<form action="check-user" method="post">



					<caption>
						<h2>User Login</h2>
					</caption>
					<span style="color: red; font-size: 120%"> <c:if
							test="${message != null}">
							<c:out value='${message}' />
						</c:if>
					</span>

					<fieldset class="form-group">
						<label>username</label> <input type="text" class="form-control"
							name="username" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>password</label> <input type="password"
							class="form-control" name="password" required="required">
					</fieldset>
					<button type="submit" class="btn btn-success">Login</button>

				</form>



				<div>
					<span style="color: #883333;"><a
						href="<%=request.getContextPath()%>/new-user" class="nav-link">Register
							New User</a></span>
				</div>
			</div>
		</div>
	</div>
</body>

</html>