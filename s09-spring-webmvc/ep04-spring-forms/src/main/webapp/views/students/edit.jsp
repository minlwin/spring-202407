<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<app:layout title="Students" active="students">

	<h4><i class="bi-pencil"></i> Edit Student</h4>
	
	<form:form method="post" modelAttribute="studentForm" cssClass="mt-3">
	
		<form:hidden path="id" />
		
		<div class="row mb-3">
			<div class="col-4">
				<!-- Name -->
				<label class="form-label">Student Name</label>
				<form:input path="name" type="tel" cssClass="form-control" placeholder="Enter Student Mame"/>
				<form:errors path="name" />
			</div>
			
			<div class="col-4">
				<!-- Last Education -->
				<label class="form-label">Last Education</label>
				<form:select path="education" cssClass="form-select">
					<option value="">Select One</option>
					<c:forEach var="item" items="${educations}">
						<option ${(param.education eq item or studentForm.education eq item) ? 'selected' : ''} value="${item}">${item}</option>
					</c:forEach>
				</form:select>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-4">
				<!-- Phone -->
				<label class="form-label">Phone Number</label>
				<form:input path="phone" type="tel" cssClass="form-control" placeholder="Enter Phone Number"/>
				<form:errors path="phone" />
			</div>
			
			<div class="col-4">
				<!-- Email -->
				<label class="form-label">Email Address</label>
				<form:input path="email" type="email" cssClass="form-control" placeholder="Enter Email Address"/>
				<form:errors path="email" />
			</div>
		</div>
		
		<div class="mb-3">
			<label class="form-label">Remark</label>
			<form:textarea path="remark" cssClass="form-control"/>
		</div>
		
		<button class="btn btn-primary" type="submit">
			<i class="bi-save"></i> Save Student
		</button>

	</form:form>
	
</app:layout>