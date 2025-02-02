<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout title="Suppliers" menu="supplier" group="master">

	<app:page-title title="Supplier Management" icon="bi-people" />
	
	<form id="searchForm" class="row">
	
		<input id="pageInput" type="hidden" name="page" value="${result.page()}">
		<input id="sizeInput" type="hidden" name="size" value="${result.size()}">
		
		<!-- Phone -->
		<app:form-group label="Phone" cssClass="col-auto">
			<input name="phone" value="${param.phone}" placeholder="Search Phone" class="form-control" />
		</app:form-group>

		<!-- Keyword -->
		<app:form-group label="Keyword" cssClass="col-auto">
			<input name="keyword" value="${param.keyword}" placeholder="Search Keyword" class="form-control" />
		</app:form-group>
		
		<div class="col btn-wrapper">
			<button id="searchBtn" type="button" class="btn btn-outline-primary">
				<i class="bi-search"></i> Search
			</button>
		</div>				
	</form>
	
	<table class="table table-bordered table-hover mt-3">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Shop Name</th>
				<th>Phone</th>
				<th>Email</th>
				<th>Address</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${result.contents()}" var="item">
			<tr>
				<td>${item.id()}</td>
				<td>${item.name()}</td>
				<td>${item.shopName()}</td>
				<td>${item.phone()}</td>
				<td>${item.email()}</td>
				<td>${item.address()}</td>
				<td class="text-center">
					<a href="${root}/admin/supplier/${item.id()}">
						<i class="bi-arrow-right"></i>
					</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>	
	
	<app:pagination pageResult="${result}" />
			
</app:layout>