<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<main>

	<form action="${root}/admin/supplier/${details.id()}/product" id="productSearchForm" class="d-flex">
		<input id="productPage" type="hidden" name="page" value="0" />
		<input id="productSize" type="hidden" name="size" value="10" />
		
	</form>
	
	<table class="table table-bordered table-hover mt-3">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Category</th>
				<th class="text-end">Stock</th>
				<th class="text-end">Sell Price</th>
				<th></th>
			</tr>
		</thead>
		<tbody id="productList" data-details="${root}/admin/product">
		
		</tbody>	
	</table>
	
	<div id="productPagination" class="d-flex justify-content-between">
		
		<div class="d-flex">
			<app:input-group label="Total Pages" cssClass="me-2">
				<span id="productTotalPages" class="form-control"></span>
			</app:input-group>
			
			<app:input-group label="Total Count">
				<span id="productCount" class="form-control"></span>
			</app:input-group>
			
		</div>
		
		<div class="d-flex">

			<app:input-group label="Page Size">
				<select id="productSizeSelect" class="form-select">
					<option value="5">5</option>
					<option value="10" selected="selected">10</option>
					<option value="25">25</option>
				</select>
			</app:input-group>
			
			<div id="productPageLinks">
			
			</div>
			
		</div>		
	
	</div>
	
	<script type="text/javascript" src="${root}/resources/js/supplier-details-product.js"></script>
</main>