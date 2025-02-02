<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<main>

	<form action="${root}/admin/product/${details.id()}/stock" id="stockSearchForm" class="d-flex">
		<input id="stockPage" type="hidden" name="page" value="0" />
		<input id="stockSize" type="hidden" name="size" value="10" />
		
	</form>
	
	<table class="table table-bordered table-hover mt-3">
		<thead>
			<tr>
				<th>Change Date</th>
				<th>Action</th>
				<th>Opponent</th>
				<th class="text-end">Before</th>
				<th class="text-end">Amount</th>
				<th class="text-end">Stock</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody id="stockList" data-details="${root}/admin">
		
		</tbody>	
	</table>
	
	<div id="stockPagination" class="d-flex justify-content-between">
		
		<div class="d-flex">
			<app:input-group label="Total Pages" cssClass="me-2">
				<span id="stockTotalPages" class="form-control"></span>
			</app:input-group>
			
			<app:input-group label="Total Count">
				<span id="stockCount" class="form-control"></span>
			</app:input-group>
			
		</div>
		
		<div class="d-flex">

			<app:input-group label="Page Size">
				<select id="stockSizeSelect" class="form-select">
					<option value="5">5</option>
					<option value="10" selected="selected">10</option>
					<option value="25">25</option>
				</select>
			</app:input-group>
			
			<div id="stockPageLinks">
			
			</div>
			
		</div>		
	
	</div>
	
	<script type="text/javascript" src="${root}/resources/js/product-details-stock.js"></script>

</main>