<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet Scope</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container mt-4">
		<h3>Servlet Scopes</h3>
		
		
		<div class="list-group w-50 mb-3">
			<div class="list-group-item d-flex justify-content-between">
				Application Scope
				<span>
					<c:out value="${applicationScope.counter.count}"></c:out>
				</span>
			</div>
			<div class="list-group-item d-flex justify-content-between">
				Session Scope
				<span>
					<c:out value="${sessionScope.counter.count}"></c:out>
				</span>
			</div>
			<div class="list-group-item d-flex justify-content-between">
				Request Scope
				<span>
					<c:out value="${requestScope.counter.count}"></c:out>
				</span>
			</div>
		</div>
		
		<a href="counter" class="btn btn-primary">Count Up</a>
	</div>

</body>
</html>