<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container mt-4">
		<h3>Spring MVC</h3>
		
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Capital</th>
					<th>Region</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="region" items="${list}">
					<tr>
						<td>
							<c:url var="pathLink" value="/home/${region.id}"></c:url>
							<a href="${pathLink}">${region.id}</a>
						</td>
						<td>
							<c:url var="detailsUrl" value="/home/details">
								<c:param name="id" value="${region.id}"></c:param>
							</c:url>
							<a href="${detailsUrl}">${region.name}</a>
						</td>
						<td>${region.capital}</td>
						<td>${region.region}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>