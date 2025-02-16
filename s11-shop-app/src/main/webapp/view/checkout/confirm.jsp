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
					
					<div class="row row-cols-2 gy-3 gx-4">
						<app:form-group label="Name">
							<span class="form-control">${cart.addressForm.name}</span>
						</app:form-group>
						<app:form-group label="Building">
							<span class="form-control">${cart.addressForm.building}</span>
						</app:form-group>
						<app:form-group label="Street">
							<span class="form-control">${cart.addressForm.street}</span>
						</app:form-group>
						<app:form-group label="Quarter">
							<span class="form-control">${cart.addressForm.quarter}</span>
						</app:form-group>
						<app:form-group label="Township">
							<span class="form-control">${cart.addressForm.township}</span>
						</app:form-group>
						<app:form-group label="Region">
							<span class="form-control">${cart.addressForm.region}</span>
						</app:form-group>
					</div>
				</div>
			</div>
			
			
		</div>
		
		<div class="col">
			<!-- Cart Items -->
			<div class="card">
				<div class="card-body">
					<h5>Cart Items</h5>

					<div id="itemContainer" class="list-group list-group-flush">
						<c:forEach var="item" varStatus="status" items="${cart.items}">
						<div id="item-${item.id}" class="list-group-item d-flex justify-content-between">
							<div >
								<div>${item.product}</div>
								<div>Unit Price : ${item.price}</div>
								<div>Quantity : 
									<span id="itemQuentity-${item.id}">${item.quantity}</span>
								</div>
							</div>
							<div id="itemTotal-${item.id}">
								${item.total}
							</div>
						</div>
						</c:forEach>
						<div id="summaryRow" class="list-group-item d-flex justify-content-between">
							<div>
								Total Amount
							</div>
							<div id="totalAmount">
								${cart.totalAmount}
							</div>
						</div>
					</div>
				</div>		
			</div>
			
			<div class="mt-4 text-end">
				<a href="${root}/cart/checkout" class="btn btn-outline-primary">
					<i class="bi-arrow-left"></i> Back
				</a>
				
				<button class="btn btn-primary">
					<i class="bi-check"></i> Check Out
				</button>
			</div>
		</div>	
	</sf:form>
	<script src="${root}/resources/js/shopping-cart.js"></script>
</app:layout>