<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<%@ attribute name="pageResult" type="com.jdc.shop.model.PageInfo" required="true" %>

<div class="d-flex justify-content-between">
	<!-- Total Info -->
	<div class="d-flex">
		<app:input-group label="Total Pages" cssClass="me-2">
			<span class="form-control">${pageResult.totalPages()}</span>
		</app:input-group>
		
		<app:input-group label="Total Count">
			<span class="form-control">${pageResult.count()}</span>
		</app:input-group>
		
	</div>
	
	<div class="d-flex">
		<!-- Size Select -->
		<app:input-group label="Page Size" cssClass="me-2">
			<select class="form-select">
				<option value="5">5</option>
				<option value="10">10</option>
				<option value="25">25</option>
			</select>
		</app:input-group>
		
		<!-- First Page -->
		<c:if test="${pageResult.showFirst()}">
			<a href="#" class="btn btn-outline-primary me-1">
				<i class="bi-arrow-bar-left"></i>
			</a>
		</c:if>
		
		<!-- Links -->
		<c:forEach var="item" items="${pageResult.links()}">
			<a href="#" class="btn btn-outline-primary me-1">
				${item + 1}
			</a>
		</c:forEach>

		<!-- Last Page -->
		<c:if test="${pageResult.showLast()}">
			<a href="#" class="btn btn-outline-primary">
				<i class="bi-arrow-bar-right"></i>
			</a>
		</c:if>
	</div>
	
</div>