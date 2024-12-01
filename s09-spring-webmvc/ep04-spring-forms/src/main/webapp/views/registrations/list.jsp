<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<app:layout title="Registrations" active="registrations">

	<h4><i class="bi-flag"></i> Registration Management</h4>
	
	<!-- Search Form -->
	<form class="row">
		
		<!-- Level -->
		<div class="col-auto">
			<label class="form-label">Level</label>
			<select name="level" id="level" class="form-select">
				<option value="">Search All</option>
				<c:forEach var="item" items="${levels}">
					<option ${param.level eq item ? 'selected' : ''} value="${item}">${item}</option>
				</c:forEach>
			</select>
		</div>
		
		<!-- Start From -->
		<div class="col-auto">
			<label class="form-label">Register From</label>
			<input type="date" value="${param.from}" class="form-control" name="from" />
		</div>
				
		<!-- Start To -->
		<div class="col-auto">
			<label class="form-label">Register To</label>
			<input type="date" value="${param.to}" class="form-control" name="to" />
		</div>
		
		<!-- Keyword -->
		<div class="col-auto">
			<label class="form-label">Keyword</label>
			<input type="text" value="${param.keyword}" class="form-control" name="keyword" placeholder="Search Keyword" />
		</div>
		
		<!-- Search Button -->
		<div class="col btn-wrapper">
			<button type="submit" class="btn btn-primary"><i class="bi-search"></i> Search</button>
		</div>
		
	</form>
	
	<!-- Registration List -->
	<table class="table table-striped mt-3">
		<thead>
			<tr>
				<th>Id</th>
				<th>Level</th>
				<th>Course</th>
				<th>Start Date</th>
				<th>Registered At</th>
				<th>Student Name</th>
				<th>Phone</th>
				<th>Email</th>
				<th>Fees</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach var="item" items="${list}">
			<tr>
				<td>${item.id().code}</td>
				<td>${item.level()}</td>
				<td>${item.courseName()}</td>
				<td>${item.startAt()}</td>
				<td>${localDateTimes.format(item.registedAt())}</td>
				<td>${item.studentName()}</td>
				<td>${item.studentPhone()}</td>
				<td>${item.studentEmail()}</td>
				<td>${item.fees()}</td>
				<td>
					<a href="${pageContext.request.contextPath}/registrations/${item.id().code}" class="icon-link">
						<i class="bi-send"></i>
					</a>
				</td>
			</tr>
		</c:forEach>	
		</tbody>
	
	</table>

</app:layout>