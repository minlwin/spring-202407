<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<app:layout title="Registrations" active="registrations">
	
	<h4><i class="bi-plus"></i> New Registration</h4>
	
	<form:form method="post" modelAttribute="registForm">
		
		<form:hidden path="sectionId"/>
		
		<div class="row mt-2">

			<div class="col">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"><i class="bi-person"></i> Student Information</h5>
						<div class="mb-3">
							<label class="form-label">Student Name</label>
							<form:input path="name" cssClass="form-control" placeholder="Enter Student Name"/>	
							<form:errors path="name"></form:errors>			
						</div>
					
						<div class="mb-3">
							<label class="form-label">Phone Number</label>
							<form:input path="phone" cssClass="form-control" type="tel" placeholder="Enter Phone Number"/>	
							<form:errors path="phone"></form:errors>			
						</div>
			
						<div class="mb-3">
							<label class="form-label">Email Address</label>
							<form:input path="email" cssClass="form-control" type="email" placeholder="Enter Email Address"/>	
							<form:errors path="email"></form:errors>			
						</div>		
						
					</div>
				</div>
			</div>
						
			<div class="col">
				<div class="card h-100">
					<div class="card-body">
						<h5 class="card-title"><i class="bi-calendar"></i> Class Information</h5>		
						
						<div class="mb-3">
							<label class="form-label">Course Name</label>
							<span class="form-control">${registForm.course}</span>
						</div>
						<div class="mb-3">
							<label class="form-label">Start Date</label>
							<span class="form-control">${registForm.startAt}</span>
						</div>
						<div class="mb-3">
							<label class="form-label">Fees</label>
							<span class="form-control">
								<fmt:formatNumber value="${registForm.fees}" /> MMK
							</span>
						</div>
					</div>
				</div>
			</div>			

		</div>
		
		<div class="card mt-4">
			<div class="card-body">
				<div class="mb-3">
					<label class="form-label">Remark</label>
					<form:textarea path="remark" cssClass="form-control" placeholder="Registration Remark"/>
				</div>
				
				<button type="submit" class="btn btn-primary">
					<i class="bi-save"></i> Save Registration
				</button>
			</div>
		</div>
	
	</form:form>

</app:layout>