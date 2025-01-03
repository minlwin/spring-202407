<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>    

<app:layout title="Courses" active="courses">
	<h4><i class="bi-book"></i> Course Management</h4>
	
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
			<button type="submit" class="btn btn-primary"><i class="bi-search"></i> Search</button>
			<a href="/courses/edit" class="btn btn-outline-primary"><i class="bi-plus"></i> Add New</a>
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
				<th></th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${list}" var="item">
			<tr>
				<td>${item.id()}</td>
				<td>${item.name()}</td>
				<td>${item.level()}</td>
				<td>${item.hours()}</td>
				<td>${item.fees()}</td>
				<td>${localDateTimes.format(item.createdAt())}</td>
				<td>${item.sections()}</td>
				<td>
					<a class="icon-link" href="${pageContext.request.contextPath}/courses/${item.id()}">
						<i class="bi-send"></i>
					</a>
				</td>
			</tr>			
			</c:forEach>
		</tbody>
	
	</table>
	
</app:layout>