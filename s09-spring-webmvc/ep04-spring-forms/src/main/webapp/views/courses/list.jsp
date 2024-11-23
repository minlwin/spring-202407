<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    

<app:layout title="Courses" active="courses">
	<h4>Course Management</h4>
	
	<!-- Search Form -->
	<form class="row">
		<!-- Level -->
		<div class="col-auto">
			<label for="level" class="form-label">Level</label>
			<select name="level" id="level" class="form-select">
				<option value="">Search All</option>
				<c:forEach var="item" items="${levels}">
					<option ${param.level eq item ? 'selected' : ''} value="${item}">${item}</option>
				</c:forEach>
			</select>
		</div>
		
		<!-- Name -->
		<div class="col-auto">
			<label for="name" class="form-label">Course Name</label>
			<input type="text" value="${param.name}" name="name" id="name" class="form-control" placeholder="Search Name" />
		</div>
		
		<div class="col btn-wrapper">
			<button type="submit" class="btn btn-primary">Search</button>
			<a href="/courses/edit" class="btn btn-outline-primary">Add New</a>
		</div>
	</form>
	
	<!-- Result Table -->
	<table class="table table-striped mt-3">
		
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Level</th>
				<th>Hours</th>
				<th>Fees</th>
				<th>Created At</th>
				<th>Sections</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${list}" var="item">
			<tr>
				<td>${item.id()}</td>
				<td>
					<a href="${pageContext.request.contextPath}/courses/${item.id()}">${item.name()}</a>
				</td>
				<td>${item.level()}</td>
				<td>${item.hours()}</td>
				<td>${item.fees()}</td>
				<td><c:out value="${item.createdAt()}"></c:out></td>
				<td>${item.sections()}</td>
			</tr>			
			</c:forEach>
		</tbody>
	
	</table>
	
</app:layout>