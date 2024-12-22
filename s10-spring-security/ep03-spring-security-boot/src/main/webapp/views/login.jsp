<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<app:layout title="Login">
	
	<h3><i class="bi-unlock"></i> Member Login</h3>
	
	<form:form method="post" cssClass="w-25">
		<app:form-group label="Login ID">
			<input type="text" name="username" required="required" placeholder="Enter Login ID" class="form-control" />
		</app:form-group>
		<app:form-group label="Password">
			<input type="password" name="password" required="required" placeholder="Enter Password" class="form-control" />
		</app:form-group>
		
		<div>
			<button type="submit" class="btn btn-primary">
				<i class="bi-check"></i> Login
			</button>
			
			<a href="${pageContext.request.contextPath}/signup" class="btn btn-outline-primary">
				<i class="bi-person-plus"></i> Sign Up
			</a>
		</div>
	</form:form>
	
</app:layout>