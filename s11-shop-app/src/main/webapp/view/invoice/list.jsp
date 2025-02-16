<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout title="Invoice" menu="Invoice" >
	
	<app:page-title title="Invoices" icon="bi-calendar" />
	
	<!-- Search Form -->
	<form id="searchForm" class="row">
	
		<input id="pageInput" type="hidden" name="page" value="${pageResult.page()}" />
		<input id="sizeInput" type="hidden" name="size" value="${pageResult.size()}" />

		<input type="hidden" name="customerId" value="${customerId}" />
		
		<!-- Status -->
		<app:form-group label="Status" cssClass="col-auto">
			<select name="status" class="form-select">
				<option value="">Search All</option>
				<c:forEach items="${statuses}" var="item">
					<option value="${item}" ${param.status eq item ? 'selected' : ''}>${item}</option>
				</c:forEach>
			</select>
		</app:form-group>
		
		<!-- Date From -->
		<app:form-group label="Date From" cssClass="col-auto">
			<input type="date" name="dateFrom" class="form-control" value="${param.dateFrom}" />
		</app:form-group>
		
		<!-- Date To -->
		<app:form-group label="Date To" cssClass="col-auto">
			<input type="date" name="dateTo" class="form-control" value="${param.dateTo}" />
		</app:form-group>
		
		<!-- Keyword -->
		<app:form-group label="Date From" cssClass="col-auto">
			<input name="keyword" class="form-control" placeholder="Search Keyword" value="${param.keyword}" />
		</app:form-group>
 		
		<!-- Search Button -->
		<div class="col-auto btn-wrapper">
			<button id="searchBtn" type="button" class="btn btn-primary">
				<i class="bi-search"></i> Search
			</button>
		</div>
	</form>
	
	<!-- Result Table -->
	<table class="table table-bordered table-striped table-hover mt-3">
		<thead>
			<tr>
				<th>Invoice ID</th>
				<th>Customer</th>
				<th>Phone</th>
				<th>Email</th>
				<th>Sale At</th>
				<th>Status</th>
				<th>Remark</th>
				<th class="text-end">Total Count</th>
				<th class="text-end">Total Amount</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach var="item" items="${result.contents()}">
			<tr>
				<td>${item.id().code}</td>
				<td>${item.customerName()}</td>
				<td>${item.customerPhone()}</td>
				<td>${item.customerEmail()}</td>
				<td>${formatter.dateTime(item.saleAt())}</td>
				<td>${item.status()}</td>
				<td>${item.remark()}</td>
				<td class="text-end">
					<fmt:formatNumber value="${item.items()}" />
				</td>
				<td class="text-end">
					<fmt:formatNumber value="${item.totals()}" />
				</td>
				<td class="text-center">
					<a href="${root}/${detailsPath}/${item.id().code}">
						<i class="bi-arrow-right"></i>
					</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	
	<!-- Pagination -->
	<app:pagination pageResult="${result}" />
	
</app:layout>