<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout title="Categories" menu="category" group="master">
	
	<app:page-title title="Category Management" icon="bi-tags" />
	
	<form class="row">
		<app:form-group label="Create From" cssClass="col-auto">
			<input name="from" value="${param.from}" type="date" class="form-control" />
		</app:form-group>
		<app:form-group label="Create To" cssClass="col-auto">
			<input name="to" value="${param.to}" type="date" class="form-control" />
		</app:form-group>
		<app:form-group label="Keyword" cssClass="col-auto">
			<input name="keyword" value="${param.keyword}" class="form-control" placeholder="Search Keyword" />
		</app:form-group>
		<div class="col btn-wrapper">
			<button class="btn btn-outline-primary">
				<i class="bi-search"></i> Search
			</button>
			
			<a href="#" id="uploadBtn" onclick="return false;" class="btn btn-outline-primary">
				<i class="bi-upload"></i> Upload
			</a>
			
			<a href="#" onclick="return false;" class="btn btn-outline-primary">
				<i class="bi-plus"></i> Add New
			</a>
			
		</div>
	</form>
	
	<!-- Result Table -->
	<div class="mt-3">
		<c:choose>
			<c:when test="${empty list}">
				<app:alert message="There is no category. Please upload or add new category." />
			</c:when>
			
			<c:otherwise>
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Created At</th>
							<th>Created By</th>
							<th>Updated At</th>
							<th>Updated By</th>
							<th class="text-end">Products</th>
							<th></th>
						</tr>
					</thead>
					
					<tbody>
					<c:forEach var="item" items="${list}">
						<tr>
							<td>${item.id()}</td>
							<td>${item.name()}</td>
							<td>${formatter.dateTime(item.createdAt())}</td>
							<td>${item.createdBy()}</td>
							<td>${formatter.dateTime(item.updatedAt())}</td>
							<td>${item.updatedBy()}</td>
							<td class="text-end">${products}</td>
							<td class="text-center">
								<a href="#" class="icon-link">
									<i class="bi-pencil"></i>
								</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>

	<form id="uploadForm" enctype="multipart/form-data" class="d-none" action="${root}/admin/category/upload" method="post">
		<sec:csrfInput/>
		
		<input id="uploadInput" type="file" name="file" />
	</form>
	
	<script src="${root}/resources/js/category.js"></script>
</app:layout>