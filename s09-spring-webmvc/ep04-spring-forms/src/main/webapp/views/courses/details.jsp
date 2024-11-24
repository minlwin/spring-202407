<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    

<app:layout title="Courses" active="courses">
	
	<c:set var="sections" value="${details.sections()}"></c:set>
	
	
	<h4><i class="bi-book"></i> ${details.name()}</h4>
	
	<div class="row">
		<!-- Level -->
		<div class="col-auto">
			<span class="text-secondary">Level : </span>
			<span>${details.level()}</span>
		</div>
		<!-- Fees -->
		<div class="col-auto">
			<span class="text-secondary">Fees : </span>
			<span>${details.fees()} MMK</span>
		</div>
		<!-- Hours -->
		<div class="col-auto">
			<span class="text-secondary">Duration : </span>
			<span>${details.hours()} Hours</span>
		</div>
		<!-- Created At -->
		<div class="col">
			<span class="text-secondary">Created At : </span>
			<span>${localDateTimes.format(details.createdAt())}</span>
		</div>
	</div>
	
	<!-- Description -->
	<p>${details.description()}</p>
	
	<p5>Sections</p5>
	<!-- Sections -->
	<c:choose>
		<c:when test="${sections.size() > 0}">
			
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Start At</th>
						<th>Months</th>
						<th>Fees</th>
						<th>Available Seats</th>
						<th>Registrations</th>
					</tr>
				</thead>
				
				<tbody>
				<c:forEach items="${sections}" var="item">
					<tr>
						<td>${item.id()}</td>
						<td>${item.startAt()}</td>
						<td>${item.months()}</td>
						<td>${item.fees()}</td>
						<td>${item.seats()}</td>
						<td>${item.registrations()}</td>
					</tr>
				</c:forEach>	
				</tbody>
			</table>
			
		</c:when>
		<c:otherwise>
			<div class="alert alert-info">
				There is no sections.
			</div>
		</c:otherwise>
	</c:choose>
	
	<div>
		<c:url var="editCourse" value="/courses/edit">
			<c:param name="id" value="${details.id()}"></c:param>
		</c:url>
		<a href="${editCourse}" class="btn btn-primary">
			<i class="bi-pencil"></i> Edit Course
		</a>
		<c:url var="addNewSection" value="/sections/edit">
			<c:param name="courseId" value="${details.id()}"></c:param>
		</c:url>
		<a href="${addNewSection}" class="btn btn-outline-primary">
			<i class="bi-plus"></i> Add New Section
		</a>
	</div>
	
</app:layout>