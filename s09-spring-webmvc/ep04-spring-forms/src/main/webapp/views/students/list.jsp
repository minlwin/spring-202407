<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    

<app:layout title="Students" active="students">
	
	<h4><i class="bi-people"></i> Student Management</h4>
	
	<!-- Search Bar -->
	<form class="row">
		
		<!-- Level -->
		<div class="col-auto">
			<label class="form-label">Level</label>
			<select name="level" id="level" class="form-select">
				<option value="">Search All</option>
				<c:forEach var="item" items="${educations}">
					<option ${param.education eq item ? 'selected' : ''} value="${item}">${item}</option>
				</c:forEach>
			</select>
		</div>
		
		<!-- Start From -->
		<div class="col-auto">
			<label class="form-label">Entrance From</label>
			<input name="entryFrom" value="${param.entryFrom}" type="date" class="form-control" />
		</div>
				
		<!-- Start To -->
		<div class="col-auto">
			<label class="form-label">Entrance To</label>
			<input name="entryTo" value="${param.entryTo}" type="date" class="form-control" />
		</div>
		
		<!-- Keyword -->
		<div class="col-auto">
			<label class="form-label">Keyword</label>
			<input name="keyword" value="${param.keyword}" type="text" class="form-control" placeholder="Search Keyword" />
		</div>
		
		<!-- Search Button -->
		<div class="col btn-wrapper">
			<button type="submit" class="btn btn-primary"><i class="bi-search"></i> Search</button>
		</div>
		
	</form>
	
	<table class="table table-striped mt-3">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Entrance Date</th>
				<th>Phone</th>
				<th>Email</th>
				<th>Last Education</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach var="item" items="${list}">
			<tr>
				<td>${item.id()}</td>
				<td>${item.name()}</td>
				<td>${localDateTimes.format(item.entryAt())}</td>
				<td>${item.phone()}</td>
				<td>${item.email()}</td>
				<td>${item.education()}</td>
				<td>
					<a class="icon-link" href="${pageScope.request.contextPath}/students/${item.id()}">
						<i class="bi-send"></i>
					</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>		
</app:layout>