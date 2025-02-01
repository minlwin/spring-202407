<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
    
<app:layout title="Purchase" menu="purchase">

	<app:page-title title="Purchase Management" icon="bi-cart-check" />
	
	<form id="searchForm" class="d-flex">
		<c:if test="${pageResult ne null}">
			<input id="pageInput" type="hidden" name="page" value="${pageResult.page()}" />
			<input id="sizeInput" type="hidden" name="size" value="${pageResult.size()}" />
		</c:if>
		
		<app:form-group label="Status" cssClass="me-2">
			<select name="status" class="form-select">
				<option value="">All Status</option>
				<c:forEach items="${statusList}" var="item">
					<option value="${item}">${item}</option>
				</c:forEach>
			</select>
		</app:form-group>

		<app:form-group label="Date From" cssClass="me-2">
			<input name="dateFrom" type="date" class="form-control" />
		</app:form-group>
		
		<app:form-group label="Date From" cssClass="me-2">
			<input name="dateTo" type="date" class="form-control" />
		</app:form-group>
		
		<app:form-group label="Keywoord" cssClass="me-2">
			<input name="keyword" placeholder="Search Keyword" class="form-control" />
		</app:form-group>
		
		<input id="sizeInput" name="size" type="hidden" />
		<input id="pageInput" name="page" type="hidden" />
		
		<div class="btn-wrapper">
			<button id="searchBtn" type="button" class="btn btn-outline-primary">
				<i class="bi-search"></i> Search
			</button>
			
			<a href="${root}/admin/purchase/edit" class="btn btn-outline-danger">
				<i class="bi-plus"></i> New Purchase
			</a>
		</div>
	</form>
	
	<!-- List View -->
	<table class="table table-bordered table-hover mt-3">
		<thead>
			<tr>
				<th>Code</th>
				<th>Purchase Date</th>
				<th>Status</th>
				<th>Supplier</th>
				<th>Shop</th>
				<th>Phone</th>
				<th class="text-end">Items</th>
				<th class="text-end">Amount</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${result.contents()}" var="item">
			<tr>
				<td>${item.code()}</td>
				<td>${item.id().issueAt}</td>
				<td>${item.status()}</td>
				<td>${item.supplierName()}</td>
				<td>${item.shopName()}</td>
				<td>${item.phone()}</td>
				<td class="text-end">
					<fmt:formatNumber value="${item.totalItem()}" />
				</td>
				<td class="text-end">
					<fmt:formatNumber value="${item.totalAmount()}" />
				</td>
				<td class="text-center">
					
					<a href="${root}/admin/purchase/${item.code()}">
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