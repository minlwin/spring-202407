<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<app:layout title="Courses" active="courses">
	
	<h4>
		<c:choose>
			<c:when test="${courseForm.id ne null}">
				<i class="bi-pencil"></i> Edit Course
			</c:when>		
			<c:otherwise>
				<i class="bi-plus"></i> Add New Course
			</c:otherwise>
		</c:choose>
	</h4>
	
	<form:form action="${pageContext.request.contextPath}/courses/edit" method="post" modelAttribute="courseForm">
		
		<form:hidden path="id"/>
		
		<div class="mb-3 row">
			<!-- Name -->
			<div class="col-6">
				<label class="form-label" for="name">Course Name</label>
				<form:input path="name" cssClass="form-control" placeholder="Enter Course Name"/>
				<form:errors path="name"></form:errors>
			</div>
		</div>
		
		<div class="row mb-3">
			<!-- Level -->
			<div class="col-3">
				<label for="level" class="form-label">Level</label>
				<form:select path="level" cssClass="form-select">
					<option value="">Search All</option>
					<c:forEach var="item" items="${levels}">
						<option ${(param.level eq item or courseForm.level eq item) ? 'selected' : ''} value="${item}">${item}</option>
					</c:forEach>
				</form:select>
				<form:errors path="level"></form:errors>
			</div>
			<!-- Duration -->
			<div class="col-3">
				<label class="form-label" for="hours">Hours</label>
				<form:input path="hours" cssClass="form-control" type="number"/>
				<form:errors path="hours" ></form:errors>
			</div>
			
			<!-- Fees -->
			<div class="col-3">
				<label class="form-label" for="fees">Fees</label>
				<form:input path="fees" cssClass="form-control" type="number"/>
				<form:errors path="fees" ></form:errors>
			</div>
		</div>
		
		<div class="mb-3">
			<label for="description" class="form-label">Description</label>
			<form:textarea path="description" cssClass="form-control"/>
		</div>	
		
		<div class="mb-3">
			<button type="submit" class="btn btn-primary">Save Course</button>
		</div>
	</form:form>
	
</app:layout>