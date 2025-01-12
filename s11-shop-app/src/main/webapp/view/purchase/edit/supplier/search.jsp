<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<app:layout title="Purchase" menu="purchase">

	<app:page-title title="Purchase Edit" icon="bi-cart-check" />
	
	<h5>Purchase Edit > Supplier > Search</h5>
	
	<form:form action="${root}/admin/purchase/edit/supplier/select" 
		modelAttribute="supplierCode" method="post" cssClass="d-flex">
		<app:form-group label="Supplier Code" cssClass="me-2">
			<form:input path="code" cssClass="form-control" placeholder="Search Code"/>
			<form:errors path="code" cssClass="text-sm text-secondary" />			
		</app:form-group>
		
		<div class="btn-wrapper">
			<button type="button" class="btn btn-outline-primary">
				<i class="bi-search"></i> Search
			</button>
			
			<a type="button" href="${root}/admin/purchase/edit/supplier/edit" class="btn btn-outline-danger">
				<i class="bi-plus"></i> New Supplier
			</a>
			
			<button type="submit" class="btn btn-danger">
				<i class="bi-arrow-right"></i> Next
			</button>
			
		</div>
	</form:form>
	
</app:layout>