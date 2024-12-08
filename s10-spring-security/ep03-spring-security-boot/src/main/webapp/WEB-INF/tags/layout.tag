<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="true" type="java.lang.String" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Security | ${title}</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script src="${pageContext.request.contextPath}/resources/application.js"></script>

</head>
<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand bg-primary navbar-dark">
		<div class="container">
			<a href="${pageContext.request.contextPath}/" class="navbar-brand">
				<i class="bi-house"></i> Home
			</a>

			<ul class="navbar-nav">
			
				<security:authorize access="hasAuthority('Admin')">
					<li class="nav-item">
						<a href="" class="nav-link">
							<i class="bi-people"></i> Account Management
						</a>
					</li>
				</security:authorize>
			
				<security:authorize access="hasAnyAuthority('Admin', 'Member')">
				
				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li class="nav-item">
						<a id="logoutMenu" class="nav-link">
							<i class="bi-lock"></i> Logout
						</a>
					</li>					
				</security:authorize>
			
				<security:authorize access="isAnonymous()">
					<li class="nav-item">
						<a href="${pageContext.request.contextPath}/login" class="nav-link ${title eq 'Login' ? 'active' : ''}">
							<i class="bi-unlock"></i> Login
						</a>
					</li>
				</security:authorize>
			</ul>
		</div>
		
	</nav>
	
	<!-- Content -->
	<main class="mt-3 container">
		<jsp:doBody></jsp:doBody>
	</main>
	
	<security:authorize access="isAuthenticated()">
		<form id="logoutForm" class="d-none" action="${pageContext.request.contextPath}/logout" method="post">
			<security:csrfInput/>
		</form>
	</security:authorize>
</body>
</html>