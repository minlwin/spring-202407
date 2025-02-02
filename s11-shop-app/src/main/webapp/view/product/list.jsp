<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout title="Products" menu="product" group="master">

	<app:page-title title="Product Management" icon="bi-gift" />
	
	<form id="searchForm" class="row">
	
		<input id="pageInput" type="hidden" name="page" value="${result.page()}">
		<input id="sizeInput" type="hidden" name="size" value="${result.size()}">
		
		<!-- Stock From -->
		<app:form-group label="Stock From" cssClass="col-auto">
			<input name="stockFrom" value="${param.stockFrom}" type="number" placeholder="Stock From" class="form-control" />
		</app:form-group>
		
		<!-- Stock To -->
		<app:form-group label="Stock To" cssClass="col-auto">
			<input name="stockTo" value="${param.stockTo}" type="number" placeholder="Stock To" class="form-control" />
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
				<th>Category</th>
				<th>Supplier</th>
				<th>Shop</th>
				<th>Phone</th>
				<td class="text-end">Stock</td>
				<th class="text-end">Sell Price</th>
				<th class="text-center"></th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${result.contents()}" var="item">
			<tr>
				<td>${item.id()}</td>
				<td>${item.productName()}</td>
				<td>${item.category()}</td>
				<td>${item.supplier()}</td>
				<td>${item.shop()}</td>
				<td>${item.phone()}</td>
				<td class="text-end">
					<fmt:formatNumber value="${item.stock()}" />
				</td>
				<td class="text-end">
					<fmt:formatNumber value="${item.sellPrice()}" />
				</td>
				<td class="text-center">
					<a href="${root}/admin/product/${item.id()}/details">
						<i class="bi-arrow-right"></i>
					</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>		
	
	<app:pagination pageResult="${result}" />
	
</app:layout>