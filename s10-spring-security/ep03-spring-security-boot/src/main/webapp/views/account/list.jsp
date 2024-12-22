<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<app:layout title="Home">

	<h3><i class="bi-people"></i> Account Management</h3>
	
	<!-- Search Form -->
	<form action="${pageContext.request.contextPath}/admin/account" class="row">
		
		<app:form-group cssClass="col-auto" label="Status">
			<select class="form-select" name="activated">
				<option value="">All Status</option>
				<option value="false" ${param.activated eq 'false' ? 'selected="selected"' : ''}>Pending</option>
				<option value="true" ${param.activated eq 'true' ? 'selected="selected"' : ''}>Active</option>
			</select>
		</app:form-group>
		
		<app:form-group cssClass="col-auto" label="Request From">
			<input name="requestFrom" value="${param.requestFrom}" type="date" class="form-control" />
		</app:form-group>

		<app:form-group cssClass="col-auto" label="Request To">
			<input name="requestTo" value="${param.requestTo}" type="date" class="form-control" />
		</app:form-group>

		<app:form-group cssClass="col-auto" label="Account Name">
			<input name="name" value="${param.name}" type="text" placeholder="Search Account Name" class="form-control" />
		</app:form-group>
		
		<div class="col btn-wrapper">
			<button type="submit" class="btn btn-outline-primary">
				<i class="bi-search"></i> Search
			</button>
		</div>
	</form>
	
	<!-- Result List -->
	<table class="table table-bordered table-hover mt-3">
		
		<thead>
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>Status</th>
				<th>Requested At</th>
				<th>Activated At</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${list}" var="item">
			<tr>
				<td>${item.name()}</td>
				<td>${item.email()}</td>
				<td>${item.status()}</td>
				<td>${dateTimes.format(item.requestedAt())}</td>
				<td>${dateTimes.format(item.activatedAt())}</td>
				<td class="text-center">
					<a href="#" onclick="return false;" id="${item.id()}" class="icon-link statusUpdateLink">
						<i class="${item.activated() ? 'bi-x' : 'bi-check'}"></i>
					</a>
				</td>
			</tr>
		</c:forEach>	
		</tbody>
	
	</table>
	
	<form:form action="${pageContext.request.contextPath}/admin/account/status" id="statusUpdateForm" cssClass="d-none" method="post" >
		<input type="hidden" name="id" id="statusUpdateInput" />
	</form:form>
	
</app:layout>