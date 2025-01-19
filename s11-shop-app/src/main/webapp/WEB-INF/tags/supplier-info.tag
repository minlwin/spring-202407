<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<div class="card">
	<div class="card-body">
		<h5 class="card-title"><i class="bi-shop"></i> Supplier</h5>
		
		<c:if test="${fn:length(fn:trim(purchase.supplierCode)) ne 0}">
			<div class="mb-2">
				<div class="text-sm text-secondary">Supplier Code</div>
				<div>${fn:length(fn:trim(purchase.supplierCode))}</div>
			</div>
		</c:if>

		<div class="mb-2">
			<div class="text-sm text-secondary">Supplier Name</div>
			<div>${purchase.supplierName}</div>
		</div>

		<div class="mb-2">
			<div class="text-sm text-secondary">Supplier Shop</div>
			<div>${purchase.shopName}</div>
		</div>

		<div class="mb-2">
			<div class="text-sm text-secondary">Phone</div>
			<div>${purchase.phone}</div>
		</div>

		<c:if test="${not empty purchase.email}">
			<div class="mb-2">
				<div class="text-sm text-secondary">Email</div>
				<div>${purchase.email}</div>
			</div>
		</c:if>

		<c:if test="${not empty purchase.address}">
			<div class="mb-2">
				<div class="text-sm text-secondary">Address</div>
				<div>${purchase.address}</div>
			</div>
		</c:if>
	</div>
</div>