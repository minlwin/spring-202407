<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<app:layout title="Admin">

	<h1><i class="bi-door-open"></i> Login</h1>
	
	<form:form action="${pageContext.request.contextPath}/login" method="post" class="w-25">
		
		
		<div class="mb-3">
			<label class="form-label">Login ID</label>
			<input type="text" class="form-control" name="username" placeholder="Enter Login ID" />
		</div>
	
		<div class="mb-3">
			<label class="form-label">Password</label>
			<input type="password" class="form-control" name="password" placeholder="Enter Password" />
		</div>
		
		<button type="submit" class="btn btn-primary">
			Login
		</button>

	</form:form>
	
</app:layout>
