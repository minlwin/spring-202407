<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<main>

	<form action="${root}/admin/supplier/${details.id()}/purchase" id="purchaseSearchForm" class="d-flex">
		<input id="purchasePage" type="hidden" name="page" value="0" />
		<input id="purchaseSize" type="hidden" name="size" value="10" />
		
	</form>
	
	<table class="table table-bordered table-hover mt-3">
		<thead>
			<tr>
				<th>Code</th>
				<th>Purchase Date</th>
				<th>Status</th>
				<th class="text-end">Items</th>
				<th class="text-end">Amount</th>
				<th></th>
			</tr>
		</thead>
		<tbody id="purchaseList" data-details="${root}/admin/purchase">
		
		</tbody>	
	</table>
	
	<div id="purchasePagination" class="d-flex justify-content-between">
		
		<div class="d-flex">
			<app:input-group label="Total Pages" cssClass="me-2">
				<span id="purchaseTotalPages" class="form-control"></span>
			</app:input-group>
			
			<app:input-group label="Total Count">
				<span id="purchaseCount" class="form-control"></span>
			</app:input-group>
			
		</div>
		
		<div class="d-flex">

			<app:input-group label="Page Size">
				<select id="purchaseSizeSelect" class="form-select">
					<option value="5">5</option>
					<option value="10" selected="selected">10</option>
					<option value="25">25</option>
				</select>
			</app:input-group>
			
			<div id="purchasePageLinks">
			
			</div>
			
		</div>		
	
	</div>
	
	<script type="text/javascript" src="${root}/resources/js/supplier-details-purchase.js"></script>
</main>