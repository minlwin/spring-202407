<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<app:layout title="Purchase" menu="purchase">

	<app:page-title title="Purchase Edit" icon="bi-cart-check" />
	
	<h5>Purchase Edit > Supplier > Add New</h5>
	
	<form:form action="${root}/admin/purchase/edit/supplier/edit" 
		modelAttribute="supplier" method="post">
		
		<div class="row mb-3">
			<app:form-group label="Name *" cssClass="col" >
				<form:input path="name" cssClass="form-control" placeholder="Enter Supplier Name."/>
				<form:errors path="name" cssClass="text-sm text-secondary" />
			</app:form-group>
			
			<app:form-group label="Phone *" cssClass="col" >
				<form:input path="phone" cssClass="form-control" placeholder="Enter Supplier Phone."/>
				<form:errors path="name" cssClass="text-sm text-secondary" />
			</app:form-group>
	
			<app:form-group label="Email *" cssClass="col" >
				<form:input path="email" cssClass="form-control" placeholder="Enter Supplier Email."/>
				<form:errors path="name" cssClass="text-sm text-secondary" />
			</app:form-group>
		</div>
		
		<div class="row mb-3">
			<app:form-group label="Shop Name" cssClass="col-4">
				<form:input path="shopName" cssClass="form-control"  placeholder="Enter Supplier Shop Name."/>
			</app:form-group>
	
			<app:form-group label="Address" cssClass="col">
				<form:input path="Address" cssClass="form-control" placeholder="Enter Supplier Address."/>
			</app:form-group>
		</div>
		
		<div>
			<a href="${root}/admin/purchase/edit" class="btn btn-primary">
				<i class="bi-arrow-left"></i> Back
			</a>
			
			<button class="btn btn-danger">
				<i class="bi-arrow-right"></i> Next
			</button>
		</div>
	</form:form>
	
</app:layout>