<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<%@ attribute name="title" required="true" type="java.lang.String" %>
<%@ attribute name="active" required="true" type="java.lang.String" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forms | ${title}</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<link rel="stylesheet" href="/static/application.css" />

</head>
<body>

	<nav class="navbar navbar-expand bg-primary navbar-dark">
		
		<div class="container">
			<a href="/" class="navbar-brand"><i class="bi-house"></i> Using Forms</a>
			
			<ul class="navbar-nav">
				<li class="nav-item">
					<a href="/registrations" class="nav-link ${active eq 'registrations' ? 'active' : ''}"><i class="bi-flag"></i> Registrations</a>
				</li>
				<li class="nav-item">
					<a href="/students" class="nav-link ${active eq 'students' ? 'active' : ''}"><i class="bi-people"></i> Students</a>
				</li>
				<li class="nav-item">
					<a href="/sections" class="nav-link ${active eq 'sections' ? 'active' : ''}"><i class="bi-calendar"></i> Sections</a>
				</li>
				<li class="nav-item">
					<a href="/courses" class="nav-link ${active eq 'courses' ? 'active' : ''}"><i class="bi-book"></i> Courses</a>
				</li>
			</ul>
		</div>
	</nav>
	
	<div class="container mt-3">
		<jsp:doBody></jsp:doBody>
	</div>
</body>
</html>