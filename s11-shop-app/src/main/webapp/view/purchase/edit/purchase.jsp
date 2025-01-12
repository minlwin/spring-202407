<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<app:layout title="Purchase" menu="purchase">

	<app:page-title title="Purchase Edit" icon="bi-cart-check" />
	
	<h5>Purchase Edit > Supplier > Purchase Item</h5>
	
	<form:form action="${root}/admin/purchase/edit/confirm" method="post" modelAttribute="purchase">
		<div>
			<a href="${root}/${supplierUrl}" class="btn btn-primary">
				<i class="bi-arrow-left"></i> Back
			</a>			
		</div>
	</form:form>
	
</app:layout>