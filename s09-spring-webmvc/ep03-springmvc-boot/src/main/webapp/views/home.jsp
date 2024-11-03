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

<c:url var="applicationCss" value="/statics/style/application.css"></c:url>
<link rel="stylesheet" href="${applicationCss}" >

</head>
<body>

	<div class="container mt-4">
		
		<h1>Spring MVC With Spring Boots</h1>
		
		<form class="row">
			<div class="col-auto">
				<label class="form-label">Region</label>
				<select name="region" class="form-select">
					<option value="">Search All</option>
					<c:forEach var="item" items="${regions}">
						<option ${item eq param.region ? 'selected' : ''} value="${item}">${item}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="col-auto">
				<label class="form-label">Name</label>
				<input type="text" name="name" value="${param.name}" placeholder="Search Name" class="form-control" />
			</div>

			<div class="col-auto">
				<label class="form-label">Capital</label>
				<input type="text" name="capital" value="${param.capital}" placeholder="Search Capital" class="form-control" />
			</div>
			
			<div class="col btn-wrapper">
				<button type="submit" class="btn btn-primary">Search</button>
			</div>
		</form>
		
		<table class="table table-striped mt-4">
			
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Region</th>
					<th>Capital</th>
				</tr>
			</thead>
			
			<tbody>
				
			<c:forEach var="item" items="${list}">
				
				<tr>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>${item.region}</td>
					<td>${item.capital}</td>
				</tr>
				
			</c:forEach>	
				
			</tbody>
		
		</table>
		
	</div>

</body>
</html>