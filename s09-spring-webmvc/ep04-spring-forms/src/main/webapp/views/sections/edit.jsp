<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<app:layout title="Sections" active="sections">
	<h4>${sectionForm.id ne null ? 'Edit' : 'Add New'} Section</h4>
	
	<form:form method="post" modelAttribute="sectionForm">
		
		<form:hidden path="id"/>
		<form:hidden path="courseId"/>
		
		<!-- Course Information -->
		<div class="row mb-3">
			<!-- Name -->
			<div class="col-3">
				<label class="form-label">Course Name</label>
				<span class="form-control">${sectionForm.courseName}</span>
			</div>
			
			<!-- Level -->
			<div class="col-3">
				<label class="form-label">Course Level</label>
				<span class="form-control">${sectionForm.courseLevel}</span>
			</div>

			<!-- Fees -->
			<div class="col-3">
				<label class="form-label">Fees</label>
				<span class="form-control">${sectionForm.fees}</span>
			</div>
		</div>
		
		<div class="row mb-3">
			<!-- Start Date -->
			<div class="col-3">
				<label class="form-label">Start Date</label>
				<form:input path="startDate" cssClass="form-control" type="date"/>
				<form:errors path="startDate" />
			</div>
			
			<!-- Months -->
			<div class="col-3">
				<label class="form-label">Months</label>
				<form:input path="months" cssClass="form-control" type="number"/>
				<form:errors path="months" />
			</div>
			
			<!-- Available Seats -->
			<div class="col-3">
				<label class="form-label">Available Seats</label>
				<form:input path="availableSeats" cssClass="form-control" type="number"/>
				<form:errors path="availableSeats" />
			</div>
		</div>
		
		<div class="mb-3">
			<label class="form-label">Remark</label>
			<form:textarea path="remark" cssClass="form-control" placeholder="Enter Remark"/>			
		</div>
		
		<button type="submit" class="btn btn-primary">Save Section</button>	
	</form:form>

</app:layout>