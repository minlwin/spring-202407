<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout title="Customers" menu="customer" group="master">

	<app:page-title title="Customer Management" icon="bi-people" />
	
	<!-- Search Form -->
	<form class="row" id="searchForm">
		<input id="pageInput" type="hidden" name="page" value="${result.page()}">
		<input id="sizeInput" type="hidden" name="size" value="${result.size()}">
	
		<!-- From -->
		<app:form-group label="Registered From" cssClass="col-auto">
			<input name="from" value="${param.from}" type="date" class="form-control" />
		</app:form-group>
		
		<!-- To -->
		<app:form-group label="Registered To" cssClass="col-auto">
			<input name="to" value="${param.to}" type="date" class="form-control" />
		</app:form-group>
		
		<!-- Keyword -->
		<app:form-group label="Keyword" cssClass="col-auto">
			<input name="keyword" value="${param.keyword}" type="text" class="form-control" placeholder="Search Keyword" />
		</app:form-group>
		
		<!-- Search Button -->
		<div class="col btn-wrapper">
			<button type="button" id="searchBtn" class="btn btn-outline-primary">
				<i class="bi-search"></i> Search
			</button>
		</div>
	</form>
	
	<!-- Result Table -->
	<table class="table table-bordered table-hover mt-3">
		<thead>
			<tr>
				<th>Name</th>
				<th>Registered At</th>
				<th>Gender</th>
				<th>Date of Birth</th>
				<th>Phone</th>
				<th>Email</th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach var="item" items="${page.contents()}">
			<tr>
				<td>${item.name()}</td>
				<td>${formatter.dateTime(item.registeredAt())}</td>
				<td>${item.gender()}</td>
				<td>${formatter.date(item.dob())}</td>
				<td>${item.phone()}</td>
				<td>${item.email()}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<!-- Pagination -->
	<app:pagination pageResult="${result}" />

</app:layout>