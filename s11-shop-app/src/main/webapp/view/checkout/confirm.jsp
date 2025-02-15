<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout title="Check Out" menu="Check Out" >

	<app:page-title title="Check Out" icon="bi-cart" />
	
	<sf:form action="${root}/cart/checkout" modelAttribute="cart" method="post" class="row">
		<div class="col-8">
			
			<div class="card">
				<div class="card-body">
					<h5>Shipping Address</h5>
					
				</div>
			</div>
			
			
		</div>
		
		<div class="col">
			<!-- Cart Items -->
			<div class="card">
				<div class="card-body">
					<h5>Cart Items</h5>
					
				</div>
			</div>		
		</div>
	</sf:form>
	<script src="${root}/resources/js/shopping-cart.js"></script>
</app:layout>