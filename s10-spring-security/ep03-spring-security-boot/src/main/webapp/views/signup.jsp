<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<app:layout title="Sign Up">
	
	<h3><i class="bi-person-plus"></i> Sign Up</h3>
	
	<form:form method="post" modelAttribute="signUpForm" cssClass="w-25">

		<app:form-group label="Member Name">
			<form:input path="name" placeholder="Enter Member Name" cssClass="form-control" />
			<form:errors path="name" cssClass="text-secondary" />			
		</app:form-group>

		<app:form-group label="Email for Login">
			<form:input path="email" placeholder="Enter Email Address" cssClass="form-control" type="email"/>
			<form:errors path="email" cssClass="text-secondary" />			
		</app:form-group>

		<app:form-group label="Password">
			<form:password path="password" placeholder="Enter Password" class="form-control"/>
			<form:errors path="password" cssClass="text-secondary" />			
		</app:form-group>
		
		<div>
			<button type="submit" class="btn btn-primary">
				<i class="bi-person-plus"></i> Sign Up
			</button>
			
			<a href="${pageContext.request.contextPath}/signup" class="btn btn-outline-primary">
				<i class="bi-check"></i> Login
			</a>
		</div>
	</form:form>
	
</app:layout>