<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<app:layout title="Purchase" menu="purchase">

	<app:page-title title="Select Supplier" icon="bi-cart-check" />
	
	<h5>Purchase Edit > Supplier </h5>

	<c:if test="${not empty purchase.errors}">
		<div id="errors" class="alert alert-info my-3">
			<ul>
			<c:forEach var="item" items="${purchase.errors}">
				<li>${item}</li>
			</c:forEach>
			</ul>
		</div>
	</c:if>

	<form:form method="post" modelAttribute="purchase"
		action="${root}/admin/purchase/edit/purchase" cssClass="mt-4">
		
		<form:hidden path="mode" id="modeInput"/>

		<!-- Search Supplier -->
		<div id="searchForm" class="mb-4 ${purchase.mode eq 'Search' ? '' : 'd-none'}">
			<app:form-group label="Supplier Code" cssClass="w-25 mb-3">
				<div class="input-group">
					<form:input path="supplierCode" placeholder="Supplier Code" cssClass="form-control" />
					<button type="button" class="input-group-text">
						<i class="bi-search"></i>
					</button>
				</div>
			</app:form-group>
		</div>
		
		<!-- Create Supplier -->
		<div id="createForm" class="row row-cols-4 g-3 mb-4  ${purchase.mode eq 'Search' ? 'd-none' : ''}">		
			<app:form-group label="Supplier Name" cssClass="col">
				<form:input path="supplierName" cssClass="form-control" placeholder="Supplier Name"/>
			</app:form-group>
			
			<app:form-group label="Shop Name" cssClass="col">
				<form:input path="shopName" cssClass="form-control" placeholder="Shop Name"/>
			</app:form-group>
			
			<div class="col-6"></div>
			
			<app:form-group label="Phone Number" cssClass="col">
				<form:input path="phone" cssClass="form-control" placeholder="Phone Number"/>
			</app:form-group>
			
			<app:form-group label="Email Address" cssClass="col-6">
				<form:input path="email" cssClass="form-control" placeholder="Email Address"/>
			</app:form-group>

			<div class="col"></div>

			<app:form-group label="Shop Address" cssClass="col-9" >
				<form:textarea path="address" cssClass="form-control" placeholder="Shop Address"/>
			</app:form-group>
		</div>
				
		<div>
			<button id="searchBtn" type="button" class="btn btn-primary  ${purchase.mode eq 'Search' ? 'd-none' : ''}">
				<i class="bi-search"></i> Search By Code
			</button>

			<button id="createBtn" type="button" class="btn btn-primary  ${purchase.mode eq 'Search' ? '' : 'd-none'}">
				<i class="bi-plus"></i> Create Supplier
			</button>
			
			<button type="submit" class="btn btn-primary">
				<i class="bi-cart"></i> Add Purchase Items
			</button>
		</div>
		
	</form:form>
	
	<script src="${root}/resources/js/purchase-edit-supplier.js" type="text/javascript"></script>
</app:layout>