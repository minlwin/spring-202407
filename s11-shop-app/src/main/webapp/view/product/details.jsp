<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout title="Products" menu="product" group="master">

	<div class="d-flex justify-content-between">
		<app:page-title title="${details.name()}" icon="bi-gift" />
		
		<div>
			<button class="btn btn-primary">
				<i class="bi-camera"></i> Upload Photos
			</button>		
			<a href="#" class="btn btn-primary">
				<i class="bi-pencil"></i> Edit Product
			</a>
		</div>
	</div>
	
	<nav class="nav nav-tabs">
		<button class="nav-link active" data-bs-toggle="tab" data-bs-target="#info">
			<i class="bi-info"></i> Product Information
		</button>
		<button class="nav-link" data-bs-toggle="tab" data-bs-target="#history">
			<i class="bi-list"></i> Stock History
		</button>
	</nav>
	
	<div class="tab-content pt-3">
		<div id="info" class="tab-pane fade show active">
			<div class="row">
				<div class="col-3">
					<!-- Product Information -->
					<div class="list-group">
						<div class="list-group-item">
							<span class="text-secondary">Product Name</span>
							<div class="fs-5">${details.name()}</div>
						</div>
						<div class="list-group-item">
							<span class="text-secondary">Category</span>
							<div class="fs-5">${details.category()}</div>
						</div>
						<div class="list-group-item">
							<span class="text-secondary">Sell Price</span>
							<div class="fs-5">
								<fmt:formatNumber value="${details.salePrice()}" />
							</div>
						</div>
						<div class="list-group-item">
							<span class="text-secondary">Stock</span>
							<div class="fs-5">
								<fmt:formatNumber value="${details.stock()}" />
							</div>
						</div>
					</div>
				</div>
				
				<div class="col">
					<!-- Photos -->
				
				</div>
			</div>
		</div>
		<div id="history" class="tab-pane fade">
			<jsp:include page="stocks.jsp" />
		</div>
	</div>
	

</app:layout>