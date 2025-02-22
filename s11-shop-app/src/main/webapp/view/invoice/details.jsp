<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout title="Invoice" menu="Invoice" >
	
	<app:page-title title="Invoice : ${details.id().code}" icon="bi-calendar"></app:page-title>
	<h5>${details.status()}</h5>
	
	<div class="row mt-3">
		<div class="col">
			<div class="card">
				<div class="card-body">
					
					<h5 class="card-title">
						<i class="bi-cart"></i> Invoice Items
					</h5>
				
					<div class="list-group list-group-flush">
						<!-- Sale Items -->
						<c:forEach var="item" items="${details.items()}">
						<div class="list-group-item d-flex">
							<!-- Cover Image -->
							<div class="detilasImage">
								<img class="img-thumbnail" src="${root}/resources/${item.coverPhoto}" alt="Cover Photo" />
							</div>
							
							<!-- Product Info -->
							<div class="flex-grow-1 ps-4">
								<h5>${item.productName()}</h5>
								<div class="text-secondary">${item.productCategory()}</div>
								<div>Price : ${item.sellPrice()}</div>
								<div>Quantity : ${item.quantity()}</div>
							</div>
							
							<!-- Amount -->
							<div>
								<h5>
									<fmt:formatNumber value="${item.amount}" /> MMK
								</h5>
							</div>
						</div>				
						</c:forEach>
						<div class="list-group-item text-end d-flex justify-content-between">
							<h5>Total</h5>
							<h5>
								<fmt:formatNumber value="${details.total}" /> MMK
							</h5>
						</div>
					</div>				
				</div>
			</div>
		</div>
		
		<div class="col-4">
		
			<!-- Customer Info -->
			<div class="card">
				<div class="card-body">
					<h5 class="card-title"><i class="bi-person"></i> Customer</h5>
					
					<div class="row">
						<div class="col-3">Name</div>
						<div class="col">${details.customerName()}</div>
					</div>

					<div class="row">
						<div class="col-3">Phone</div>
						<div class="col">${details.customerPhone()}</div>
					</div>

					<div class="row">
						<div class="col-3">Email</div>
						<div class="col">${details.customerEmail()}</div>
					</div>
				</div>
			</div>
			
			<!-- Shipping Address -->
			<div class="card mt-4">
				<div class="card-body">
					<h5 class="card-title"><i class="bi-map"></i> Shipping Address</h5>
					<div>${details.shippingName()} <br />${details.shippingAddress()}</div>
				</div>
			</div>
			
			<c:if test="${details.canUpdate}">
				<div class="d-flex mt-3">
				<sec:authorize access="authenticated">
					<a id="cancelBtn" class="btn btn-danger me-2">
						<i class="bi-x"></i> Cancel
					</a>
					
					<div id="cancelDialog" class="modal fade">
						<div class="modal-dialog">
							<form action="${cancelUrl}" method="post" class="modal-content">
								<sec:csrfInput/>
								
								<div class="modal-header">
									<h5 class="modal-title">Cancel Invoice</h5>
								</div>
								
								<div class="modal-body">
									<app:form-group label="Reason">
										<textarea required="required" placeholder="Enter Reason for Cancel" name="reason" rows="3" class="form-control"></textarea>
									</app:form-group>
								</div>
								
								<div class="modal-footer">
									<button class="btn btn-danger">
										<i class="bi-x"></i> Cancel
									</button>
								</div>
								
							</form>
						</div>
					</div>
					
				</sec:authorize>
			
				<sec:authorize access="hasAuthority('Admin')">
					<form action="">
						<button class="btn btn-primary">
							<i class="bi-check"></i> Delivered
						</button>
					</form>
				</sec:authorize>
				</div>
			</c:if>
		</div>
	</div>
	
	<script src="${root}/resources/js/invoice-details.js"></script>
</app:layout>