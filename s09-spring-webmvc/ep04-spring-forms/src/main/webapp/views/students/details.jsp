<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    

<app:layout title="Students" active="students">
	<h4><i class="bi-person"></i> Student Details</h4>
	
	<div class="card mt-3 mb-4">
		<div class="card body">
			<div class="card-body">
				<h5><i class="bi-info-circle"></i> Personal Information</h5>
				
				<div class="row py-3">
					<div class="col-3">
						<app:information label="Name" value="${details.name()}"></app:information>
						<app:information label="Phone" value="${details.phone()}"></app:information>
						<app:information label="Email" value="${details.email()}"></app:information>
					</div>
					
					<div class="col-4">
						<app:information label="Entrance Date" value="${localDateTimes.format(details.entryAt())}"></app:information>
						<app:information label="Last Education" value="${details.education()}"></app:information>
						<app:information label="Reamark" value="${details.remark()}"></app:information>
					</div>
				</div>
				
				<c:url var="edit" value="/students/edit">
					<c:param name="id" value="${details.id()}"></c:param>
				</c:url>
				<a href="${edit}" class="btn btn-primary">
					<i class="bi-pencil"></i> Edit Student
				</a>
			
			</div>
		</div>
	</div>
	
	<h5><i class="bi-flag"></i> Registrations</h5>
	
	<table class="mt-3 table table-striped">
		<thead>
			<tr>
				<th>Registration ID</th>
				<th>Course</th>
				<th>Start Date</th>
				<th>Registered At</th>
				<th>Months</th>
				<th>Fees</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach var="item" items="${details.registrations()}">
			<tr>
				<td>${item.id().code}</td>
				<td>${item.courseName()}</td>
				<td>${item.startAt()}</td>
				<td>${localDateTimes.format(item.registedAt())}</td>
				<td>${item.months()} Months</td>
				<td>${numbers.format(item.fees())} MMK</td>
				<td>
					<a href="${pageContest.request.contextPath}/registrations/${item.id().code}" class="icon-link">
						<i class="bi-send"></i>
					</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</app:layout>