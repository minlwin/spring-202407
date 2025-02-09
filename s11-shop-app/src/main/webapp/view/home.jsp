<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<app:layout>
	
	<div class="d-flex justify-content-between">
		<app:page-title title="Welcome!" icon="bi-emoji-smile" />
		
		<form class="row">
			<div class="col-auto">
				<div class="input-group">
					<input type="text" class="form-control" name="keyword" placeholder="Search Keyword" />
					<button class="btn btn-outline-primary input-group-text">
						<i class="bi-search"></i>
					</button>
				</div>
			</div>
		</form>
	</div>
	
	<section class="row row-cols-4 g-4 mt-2 mb-4">
		<c:forEach items="${result.contents()}" var="item">
			<div class="col">
				<div class="card">
					<img src="${root}/resources/${item.coverPhoto}" class="card-img-top home-product" />
					<div class="card-body">
						<div class="d-flex justify-content-between">
							<div>
								<div>${item.productName()}</div>
								<div>
									<fmt:formatNumber value="${item.sellPrice()}" /> MMK
								</div>
							</div>
							
							<sec:authorize access="isAnonymous or hasAuthority('Member')">
								<a href="#" class="btn-link add-to-cart">
									<i class="bi-cart-plus"></i>
								</a>
							</sec:authorize>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>	
	</section>
	
	<app:pagination pageResult="${result}" />
	
</app:layout>