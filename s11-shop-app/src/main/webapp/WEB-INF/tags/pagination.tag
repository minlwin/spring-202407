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
		<app:input-group label="Page Size">
			<select id="sizeSelect" class="form-select">
				<option value="1" ${pageResult.size() eq '1' ? 'selected="selected"' : ''}>1</option>
				<option value="5" ${pageResult.size() eq '5' ? 'selected="selected"' : ''}>5</option>
				<option value="10"  ${pageResult.size() eq '10' ? 'selected="selected"' : ''}>10</option>
			</select>
		</app:input-group>
		
		<!-- First Page -->
		<c:if test="${pageResult.showFirst()}">
			<a href="#" data-page="0" onclick="return false" class="btn btn-outline-primary ms-2 pageLink">
				<i class="bi-arrow-bar-left"></i>
			</a>
		</c:if>
		
		<!-- Links -->
		<c:if test="${pageResult.totalPages() > 1}">
			<c:forEach var="item" items="${pageResult.links()}">
			
				<c:choose>
					<c:when test="${pageResult.page() ne item}">
						<a href="#" data-page="${item}" onclick="return false" class="btn btn-outline-primary ms-1 pageLink">
							${item + 1}
						</a>
					</c:when>
					
					<c:otherwise>
						<span class="btn btn-primary ms-1">
							${item + 1}
						</span>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:if>

		<!-- Last Page -->
		<c:if test="${pageResult.showLast()}">
			<a href="#" data-page="${pageResult.totalPages() - 1}" onclick="return false" class="btn btn-outline-primary ms-1 pageLink">
				<i class="bi-arrow-bar-right"></i>
			</a>
		</c:if>
	</div>
	
	<script type="text/javascript" src="${root}/resources/js/pagination.js"></script>
</div>