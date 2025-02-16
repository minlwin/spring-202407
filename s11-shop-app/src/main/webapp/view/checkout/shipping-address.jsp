<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout title="Check Out" menu="Check Out" >

	<app:page-title title="Check Out" icon="bi-cart" />
	
	<sf:form action="${root}/cart/checkout/confirm" modelAttribute="cart" method="post" class="row">
		<div class="col-8">
			
			<div class="card">
				<div class="card-body">
					<h5>Shipping Address</h5>
					
					<sf:hidden path="addressForm.id"/>
						
					<div class="row row-cols-2 gx-4 gy-3">
						<!-- Shipping Address  -->
						<app:form-group label="Name" cssClass="col">
							<sf:input path="addressForm.name" placeholder="Enter Address Name" cssClass="form-control"/>
							<sf:errors path="addressForm.name" cssClass="text-secondary" />
						</app:form-group>
						
						<app:form-group label="Building" cssClass="col">
							<sf:input path="addressForm.building" placeholder="Enter Building" cssClass="form-control"/>
							<sf:errors path="addressForm.building" cssClass="text-secondary" />
						</app:form-group>
	
						<app:form-group label="Street" cssClass="col">
							<sf:input path="addressForm.street" placeholder="Enter Street" cssClass="form-control"/>
							<sf:errors path="addressForm.street" cssClass="text-secondary" />
						</app:form-group>
	
						<app:form-group label="Quarter" cssClass="col">
							<sf:input path="addressForm.quarter" placeholder="Enter Quarter" cssClass="form-control"/>
							<sf:errors path="addressForm.quarter" cssClass="text-secondary" />
						</app:form-group>
						
						<app:form-group label="Township" cssClass="col">
							<sf:input path="addressForm.township" placeholder="Enter Township" cssClass="form-control"/>
							<sf:errors path="addressForm.township" cssClass="text-secondary" />
						</app:form-group>
	
						<app:form-group label="Region" cssClass="col">
							<sf:input path="addressForm.region" placeholder="Enter Region" cssClass="form-control"/>
							<sf:errors path="addressForm.region" cssClass="text-secondary" />
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
										<span>
											<a class="addOrRemove" href="${root}/cart/minus-one?productId=${item.id}">
												<i class="bi-dash"></i>
											</a>
										</span>
										<span id="itemQuentity-${item.id}">${item.quantity}</span>
										<span>
											<a class="addOrRemove" href="${root}/cart/plus-one?productId=${item.id}">
												<i class="bi-plus"></i>
											</a>
										</span>
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
				<button class="btn btn-primary">
					<i class="bi-check"></i> Confirm					
				</button>
			</div>
		</div>
	</sf:form>
	<script src="${root}/resources/js/shopping-cart.js"></script>
</app:layout>