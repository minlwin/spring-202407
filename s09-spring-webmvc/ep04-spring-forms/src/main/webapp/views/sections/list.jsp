<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    

<app:layout title="Sections" active="sections">
	
	<h4>Section Management</h4>
	
	<!-- Search Bar -->
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
			<label class="form-label">Start From</label>
			<input type="date" class="form-control" name="startFrom" />
		</div>
				
		<!-- Start To -->
		<div class="col-auto">
			<label class="form-label">Start To</label>
			<input type="date" class="form-control" name="startTo" />
		</div>
		
		<!-- Keyword -->
		<div class="col-auto">
			<label class="form-label">Keyword</label>
			<input type="text" class="form-control" name="keyword" placeholder="Search Keyword" />
		</div>
		
		<!-- Search Button -->
		<div class="col btn-wrapper">
			<button type="submit" class="btn btn-primary">Search</button>
		</div>
		
	</form>
	
	<!-- Result Table -->
	<table class="table table-striped mt-3">
		<thead>
			<tr>
				<th>ID</th>
				<th>Level</th>
				<th>Course</th>
				<th>Start At</th>
				<th>Months</th>
				<th>Fees</th>
				<th>Available Seats</th>
				<th>Registrations</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${list}" var="item">
			<tr>
				<td>${item.id()}</td>
				<td>${item.level()}</td>
				<td>${item.courseName()}</td>
				<td>${item.startAt()}</td>
				<td>${item.months()}</td>
				<td>${item.fees()}</td>
				<td>${item.seats()}</td>
				<td>${item.registrations()}</td>
				<td>
					<a href="${pageContext.request.contextPath}/sections/${item.id()}" class="icon-link">
						<i class="bi-send"></i>
					</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</app:layout>