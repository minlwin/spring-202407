<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
    
<app:layout title="Purchase" menu="purchase">

	<app:page-title title="Purchase Details" icon="bi-cart-check" />
	
	<div class="row mt-3">
		
		<div class="col">
			<!-- Purchase Info -->
			<div class="card mb-4">
				<div class="card-body">
					<h5 class="card-title">Purchase Info</h5>
					
					<ul class="list-group list-group-flush">
						<app:list-group-item label="Code" value="${details.id().code}" />
						<app:list-group-item label="Date" value="${details.id().issueAt}" />
						<app:list-group-item label="Status" value="${details.status()}" />
						<app:list-group-item label="Remark" value="${details.remark()}" />
					</ul>
				</div>
			</div>

			<!-- Supplier -->
			<div class="card mb-4">
				<div class="card-body">
					<h5 class="card-title">Supplier</h5>
					
					<ul class="list-group list-group-flush">
						<app:list-group-item label="Supplier" value="${details.supplier().name()}" />
						<app:list-group-item label="Shop" value="${details.supplier().shopName()}" />
						<app:list-group-item label="Phone" value="${details.supplier().phone()}" />
					</ul>
				</div>
			</div>
		</div>
		
		<div class="col-9">
			<!-- Purchase Product -->
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>No</th>
						<th>Product</th>
						<th class="text-end">Last Stock</th>
						<th class="text-end">Quantity</th>
						<th class="text-end">Buy Price</th>
						<th class="text-end">Sell Price</th>
						<th class="text-end">Amount</th>
					</tr>
				</thead>
				
				<tbody>
				<c:forEach var="item" varStatus="sts" items="${details.products()}">
					<tr>
						<td>${sts.index + 1}</td>
						<td>${item.productName()}</td>
						<td class="text-end">
							<fmt:formatNumber value="${item.lastStock()}" />
						</td>
						<td class="text-end">
							<fmt:formatNumber value="${item.quantity()}" />
						</td>
						<td class="text-end">
							<fmt:formatNumber value="${item.buyPrice()}" />
						</td>
						<td class="text-end">
							<fmt:formatNumber value="${item.sellPrice()}" />
						</td>
						<td class="text-end">
							<fmt:formatNumber value="${item.buyPrice() * item.quantity()}" />
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="6">Total Amount</td>
					<td class="text-end">
						<fmt:formatNumber value="${details.allTotal}" />
					</td>
				</tr>
				</tbody>
			</table>
		</div>
	
	</div>
	
</app:layout>