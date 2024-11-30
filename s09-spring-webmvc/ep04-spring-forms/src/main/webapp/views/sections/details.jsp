<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>   
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 

<app:layout title="Sections" active="sections">
	<h4><i class="bi-calendar"></i> ${details.courseName()} ${details.startAt()} Section</h4>
	
	<!-- Section Information -->
	<div class="row">
		<!-- Start Date -->
		<div class="col-auto">
			<span class="text-secondary">Start Date : </span>
			<span>${details.startAt()}</span>
		</div>
		
		<!-- Duration -->
		<div class="col-auto">
			<span class="text-secondary">Duration : </span>
			<span>${details.months()} Months</span>
		</div>
		
		<!-- Fees -->
		<div class="col-auto">
			<span class="text-secondary">Fees : </span>
			<span>${details.fees()} MMK</span>
		</div>
		
		<!-- Available Seats -->
		<div class="col-auto">
			<span class="text-secondary">Available Seats : </span>
			<span>${details.seats()}</span>
		</div>
	
	</div>
	
	<!-- Remark -->
	<p>${details.remark()}</p>
	
	
	<!-- Registrations List -->
	<p5>Registrations</p5>
	<c:set var="registration" value="${details.registrations()}"></c:set>
	
	<c:choose>
		
		<c:when test="${not empty registration}">
			
		</c:when>
		<c:otherwise>
			<div class="alert alert-info">
				There is no registrations.
			</div>
		</c:otherwise>
	
	</c:choose>

	<div>
		<c:url var="edit" value="/sections/edit">
			<c:param name="id" value="${details.id()}"></c:param>
		</c:url>
		<a href="${edit}" class="btn btn-primary">
			<i class="bi-pencil"></i> Edit Section
		</a>
		<c:url var="addRegistration" value="/registrations/edit">
			<c:param name="sectionId" value="${details.id()}"></c:param>
		</c:url>
		<a href="${addRegistration}" class="btn btn-outline-primary">
			<i class="bi-plus"></i> Add New Registration
		</a>
	</div>	
	
</app:layout>