<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<app:layout title="Registrations" active="registrations">
	
	<h4><i class="bi-plus"></i> New Registration</h4>
	
	<form:form method="post" modelAttribute="registForm">
		
		<!-- Section -->
		<div class="row mb-3">
			<div class="col">
				<label class="form-label">Course Name</label>
				<span class="form-control">${registForm.course()}</span>
			</div>
			<div class="col">
				<label class="form-label">Start Date</label>
				<span class="form-control">${registForm.startAt()}</span>
			</div>
			<div class="col">
				<label class="form-label">Fees</label>
				<span class="form-control">${registForm.fees()}</span>
			</div>
		</div>
		
		<!-- Student Information -->
		<div class="row mb-3">
			
			<div class="col">
				<label class="form-label">Student Name</label>
				<form:input path="name" cssClass="form-control" placeholder="Enter Student Name"/>	
				<form:errors path="name"></form:errors>			
			</div>
		
			<div class="col">
				<label class="form-label">Phone Number</label>
				<form:input path="phone" cssClass="form-control" type="tel" placeholder="Enter Phone Number"/>	
				<form:errors path="phone"></form:errors>			
			</div>

			<div class="col">
				<label class="form-label">Email Address</label>
				<form:input path="email" cssClass="form-control" type="email" placeholder="Enter Email Address"/>	
				<form:errors path="email"></form:errors>			
			</div>
		</div>
	
	</form:form>

</app:layout>