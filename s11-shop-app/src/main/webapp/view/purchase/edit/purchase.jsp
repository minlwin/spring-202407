<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<app:layout title="Purchase" menu="purchase">

	<app:page-title title="Purchase Edit" icon="bi-cart-check" />
	
	<h5>Purchase Edit > Supplier > Purchase Item</h5>
	
	<form:form  id="itemsForm" action="${root}/admin/purchase/edit/confirm" method="post" modelAttribute="purchase">
		
		<div class="row mt-4">
			<div class="col">
				<!-- Supplier Info -->
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"><i class="bi-shop"></i> Supplier</h5>
						
						<div class="mb-3">
							<div class="text-sm text-secondary">Supplier Code</div>
							<div>${purchase.supplierCode}</div>
						</div>

						<div class="mb-3">
							<div class="text-sm text-secondary">Supplier Name</div>
							<div>${purchase.supplierName}</div>
						</div>

						<div class="mb-3">
							<div class="text-sm text-secondary">Supplier Shop</div>
							<div>${purchase.shopName}</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-9">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">
							<i class="bi-cart"></i> Purchase Items
						</h5>
						
						<!-- Header -->
						<div class="row mb-2 gx-2">
							<div class="col-4">Product</div>
							<div class="col">Category</div>
							<div class="col-2">Price</div>
							<div class="col-1">Qty</div>
							<div class="col-2">Total</div>
						</div>
		
						<!-- Purchase Items -->
						<c:forEach items="${purchase.items}" var="item" varStatus="status">
							<div class="row mb-2 gx-2">
								<div class="col-4">
									<form:input path="items[${status.index}].productName" placeholder="Product Name" cssClass="form-control"/>
								</div>
								<div class="col">
									<form:input path="items[${status.index}].category" placeholder="Category Name" cssClass="form-control"/>
								</div>
								<div class="col-2">
									<form:input id="p-${status.index}" onchange="calculate(${status.index})" path="items[${status.index}].unitPrice" type="number" cssClass="form-control"/>
								</div>
								<div class="col-1">
									<form:input id="q-${status.index}" onchange="calculate(${status.index})" path="items[${status.index}].quantity" type="number" cssClass="form-control"/>
								</div>
								<div class="col-2">
									<div class="input-group">
										<span id="t-${status.index}" class="form-control">${item.total}</span>
										<c:url var="remove" value="/admin/purchase/edit/purchase/remove">
											<c:param name="index" value="${status.index}" />
										</c:url>
										<a href="${remove}" class="input-group-text removeItem">
											<i class="bi-trash"></i>
										</a>
									</div>
								</div>
							</div>
						</c:forEach>
						
						<div class="pt-2">
							<a href="${root}/admin/purchase/edit/supplier" class="btn btn-primary">
								<i class="bi-arrow-left"></i> Back
							</a>
							
							<a id="addItem" href="${root}/admin/purchase/edit/purchase/add" class="btn btn-primary">
								<i class="bi-plus"></i> Add Item
							</a>
							
							<button type="submit" class="btn btn-primary">
								<i class="bi-check"></i> Confirm
							</button>
										
						</div>
					</div>
				</div>
			</div>
		
		</div>
		
	</form:form>
	
	<script src="${root}/resources/js/purchase-item.js"></script>
	
</app:layout>