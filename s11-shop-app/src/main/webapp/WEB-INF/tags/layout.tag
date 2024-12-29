<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="title" type="java.lang.String" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Shop | ${title eq null ? "Home" : title}</title>

<c:set value="${pageContext.request.contextPath}" var="root" scope="request" />

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<link rel="stylesheet" href="${root}/resources/css/application.css" />
</head>
<body>

	<!-- Navbar -->
	<nav class="navbar navbar-expand bg-primary navbar-dark">
		<div class="container">
			<a href="${root}/" class="navbar-brand">
				<i class="bi-shop-window"></i> The Shop
			</a>
			
			<ul class="navbar-nav">
			<!-- Admin Menu -->
			<sec:authorize access="hasAuthority('Admin')">
				<li class="nav-item">
					<a href="" class="nav-link">
						<i class="bi-house"></i> Admin Home
					</a>
				</li>
			</sec:authorize>
			<!-- Customer Menu -->
			<sec:authorize access="hasAuthority('Customer')">
			
			</sec:authorize>
			<!-- Public Menu -->
			<sec:authorize access="isAnonymous()">
				<li class="nav-item">
					<a href="${root}/public/signin" class="nav-link ${title eq 'Sign In' ? 'active' : ''}">
						<i class="bi-door-open"></i> Sign In
					</a>
				</li>
			</sec:authorize>
			
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item">
					<a href="#" id="signOutMenu" onclick="return false;" class="nav-link">
						<i class="bi-door-colose"></i> Sign Out
					</a>
				</li>
			</sec:authorize>
			</ul>
		</div>
	</nav>
	
	<!-- Contents -->
	<main class="container pt-3">
		<jsp:doBody></jsp:doBody>
	</main>	
	
	<sec:authorize access="isAuthenticated()">
		<form id="signOutForm" action="${root}/logout" method="post" class="d-none">
			<sec:csrfInput/>
		</form>
	</sec:authorize>
	
	<script type="text/javascript" src="${root}/resources/js/application.js"></script>
</body>
</html>