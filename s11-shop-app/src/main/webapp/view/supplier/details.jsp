<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout title="Suppliers" menu="supplier" group="master">

	<app:page-title title="Supplier Details" icon="bi-person" />
	
	<div class="row mt-3">
		<div class="col-3">
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">Supplier Information</h5>
					
					<div class="list-group list-group-flush">
						<div class="list-group-item">
							<i class="bi-person"></i> ${details.name()}
						</div>
						<div class="list-group-item">
							<i class="bi-shop"></i> ${details.shopName()}
						</div>
						<div class="list-group-item">
							<i class="bi-telephone"></i> ${details.phone()}
						</div>
						<div class="list-group-item">
							<i class="bi-envelope"></i> ${details.email()}
						</div>
						<c:if test="${details.address() ne null and details.address() ne ''}">
							<div class="list-group-item">
								<i class="bi-map"></i> ${details.address()}
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col">
			<nav class="nav nav-tabs">
				<button class="nav-link active" data-bs-toggle="tab" data-bs-target="#purchases">
					<i class="bi-cart-check"></i> Purchases
				</button>
				<button class="nav-link" data-bs-toggle="tab" data-bs-target="#products">
					<i class="bi-gift"></i> Products
				</button>
			</nav>
			
			<div class="tab-content pt-3">
				<div id="purchases" class="tab-pane fade show active">
					<jsp:include page="purchases.jsp" />
				</div>
				<div id="products" class="tab-pane fade">
					<jsp:include page="products.jsp" />
				</div>
			</div>
		</div>
	</div>

</app:layout>