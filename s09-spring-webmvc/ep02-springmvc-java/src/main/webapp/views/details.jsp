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
		<h1>Region Details</h1>
		
		<table class="table table-bordered">
			<tr>
				<td>ID</td>
				<td>${dto.id}</td>
			</tr>
			<tr>
				<td>Name</td>
				<td>${dto.name}</td>
			</tr>
			<tr>
				<td>Capital</td>
				<td>${dto.capital}</td>
			</tr>
			<tr>
				<td>Region</td>
				<td>${dto.region}</td>
			</tr>
		</table>
		
		<c:url var="home" value="/home"></c:url>
		
		<a href="${home}" class="btn btn-primary">Home</a>
	</div>
</body>
</html>