<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
    
<app:layout title="Purchase" menu="purchase">

	<app:page-title title="Purchase Edit" icon="bi-cart-check" />
	
	<h5>Purchase Edit > Supplier > Purchase Item > Confirm</h5>
	
	<form:form action="${root}/admin/purchase/edit" method="post" modelAttribute="purchase">
		
		<div class="mt-4">
			
			<div class="mb-4">
				<!-- Supplier Info -->
				<app:supplier-info />
			</div>
			
			<h5><i class="bi-cart"></i> Purchase Items</h5>
			
			<!-- Purchase Item -->
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>No</th>
						<th>Product</th>
						<th>Category</th>
						<th class="text-end">Sell Price</th>
						<th class="text-end">Buy Price</th>
						<th class="text-end">Quantity</th>
						<th class="text-end">Amount</th>
					</tr>
				</thead>
				
				<tbody>
				<c:forEach items="${purchase.items}" var="item" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${item.productName}</td>
						<td>${item.category}</td>
						<td class="text-end">
							<fmt:formatNumber value="${item.sellPrice}" /> MMK
						</td>
						<td class="text-end">
							<fmt:formatNumber value="${item.buyPrice}" /> MMK
						</td>
						<td class="text-end">
							<fmt:formatNumber value="${item.quantity}" />
						</td>
						<td class="text-end">
							<fmt:formatNumber value="${item.total}" /> MMK
						</td>
					</tr>
				</c:forEach>
				</tbody>
				
				<tfoot>
					<tr>
						<td colspan="6" class="text-end">All Total</td>
						<td class="text-end">
							<fmt:formatNumber value="${purchase.total}" /> MMK
						</td>
					</tr>
				</tfoot>
			</table>
			
			<div>
				<a href="${root}/admin/purchase/edit/purchase" class="btn btn-primary">
					<i class="bi-arrow-left"></i> Back
				</a>
				
				<button type="submit" class="btn btn-primary">
					<i class="bi-save"></i> Save Purchase
				</button>
			</div>			
		</div>
		
	</form:form>
	
</app:layout>