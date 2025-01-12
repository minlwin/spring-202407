<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ attribute name="title" type="java.lang.String" %>
<%@ attribute name="menu" type="java.lang.String" %>
<%@ attribute name="group" type="java.lang.String" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<sec:authorize access="hasAuthority('Admin')">
	<title>Shop Admin | ${title eq null ? "Home" : title}</title>
</sec:authorize>

<sec:authorize access="!hasAuthority('Admin')">
	<title>The Shop | ${title eq null ? "Home" : title}</title>
</sec:authorize>

<c:set var="root" value="${pageContext.request.contextPath}" scope="request" />

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<link rel="stylesheet" href="${root}/resources/css/application.css" />
</head>
<body>

	<!-- Navbar -->
	<nav class="navbar navbar-expand bg-primary navbar-dark">
		<div class="container">
			<sec:authorize access="!hasAuthority('Admin')">
				<a href="${root}/" class="navbar-brand">
					<i class="bi-shop-window"></i> The Shop
				</a>
			</sec:authorize>
			<sec:authorize access="hasAuthority('Admin')">
				<a href="${root}/" class="navbar-brand">
					<i class="bi-shop-window"></i> Shop Admin
				</a>
			</sec:authorize>
			
			<ul class="navbar-nav">
			<!-- Admin Menu -->
			<sec:authorize access="hasAuthority('Admin')">
				<li class="nav-item">
					<a href="" class="nav-link">
						<i class="bi-house"></i> Admin Home
					</a>
				</li>
				
				<li class="nav-item">
					<a href="" class="nav-link">
						<i class="bi-calendar"></i> Invoices
					</a>
				</li>

				<li class="nav-item">
					<a href="${root}/admin/purchase" class="nav-link ${menu eq 'purchase' ? 'active' : ''}" >
						<i class="bi-cart"></i> Purchases
					</a>
				</li>

				<li class="nav-item dropdown">
					<a href="" class="nav-link dropdown-toggle ${group eq 'master' ? 'active' : ''}" data-bs-toggle="dropdown">
						<i class="bi-database"></i> Master
					</a>
					
					<ul class="dropdown-menu">
						<li>
							<a href="${root}/admin/category" class="dropdown-item ${menu eq 'category' ? 'active' : ''}">
								<i class="bi-tags"></i> Categories
							</a>
						</li>
						<li>
							<a href="#" class="dropdown-item" ${menu eq 'product' ? 'active' : ''}">
								<i class="bi-gift"></i> Products
							</a>
						</li>
						<li>
							<hr class="dropdown-divider" />
						</li>
						<li>
							<a href="${root}/admin/customer" class="dropdown-item ${menu eq 'customer' ? 'active' : ''}">
								<i class="bi-people"></i> Customers
							</a>
						</li>
						<li>
							<a href="#" class="dropdown-item">
								<i class="bi-people-fill"></i> Suppliers
							</a>
						</li>
					</ul>
				</li>
				
			</sec:authorize>
			<!-- Customer Menu -->
			<sec:authorize access="hasAuthority('Customer')">
			
			</sec:authorize>
			<!-- Public Menu -->
			<sec:authorize access="isAnonymous()">
				<li class="nav-item">
					<a href="${root}/public/signin" class="nav-link ${menu eq 'signIn' ? 'active' : ''}">
						<i class="bi-door-open"></i> Sign In
					</a>
				</li>
			</sec:authorize>
			
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item">
					<a href="#" id="signOutMenu" onclick="return false;" class="nav-link">
						<i class="bi-door-closed"></i> Sign Out
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