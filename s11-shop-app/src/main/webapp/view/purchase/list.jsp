<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
    
<app:layout title="Purchase" menu="purchase">

	<app:page-title title="Purchase Management" icon="bi-cart-check" />
	
	<form id="searchForm" class="d-flex">
		<c:if test="${pageResult ne null}">
			<input id="pageInput" type="hidden" name="page" value="${pageResult.page()}" />
			<input id="sizeInput" type="hidden" name="size" value="${pageResult.size()}" />
		</c:if>
		
		<!-- Date From -->
		<app:form-group label="Date From" cssClass="me-2">
			<input name="from" type="date" class="form-control" />
		</app:form-group>
		
		<!-- Date To -->
		<app:form-group label="Date From" cssClass="me-2">
			<input name="to" type="date" class="form-control" />
		</app:form-group>
		
		<!-- Keyword -->
		<app:form-group label="Date From" cssClass="me-2">
			<input name="keyword" placeholder="Search Keyword" class="form-control" />
		</app:form-group>
		
		<div class="btn-wrapper">
			<button type="button" class="btn btn-outline-primary">
				<i class="bi-search"></i> Search
			</button>
			
			<a href="${root}/admin/purchase/edit" class="btn btn-outline-danger">
				<i class="bi-plus"></i> New Purchase
			</a>
		</div>
		
	</form>
</app:layout>