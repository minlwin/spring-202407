<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout title="Sign Up">
	
	<div class="row">
		<div class="col d-flex align-items-center">
			<!-- Cover Photo -->
			<div class="me-5">
				<i class="bi-cart cover-icon"></i>
			</div>
			<div>
				<h1><i class="bi-person-plus"></i> Sign Up</h1>
				
				<p>
					Hi! There. You can search and buy our products from this site.
					If you want some other product that doesn't found in our site, you can request to us.
				</p>
				
				<p>
					You can feedback to our product. Thank you.
				</p>

				<form:form action="${root}/public/signup" modelAttribute="form" cssClass="w-50 mt-4" method="post">
					
					<app:form-group cssClass="mb-3" label="Name">
						<form:input path="name" cssClass="form-control" placeholder="Enter Name" />
						<form:errors path="name" cssClass="text-secondary" />
					</app:form-group>

					<app:form-group cssClass="mb-3" label="Phone">
						<form:input path="phone" type="tel" cssClass="form-control" placeholder="Enter Phone Number" />
						<form:errors path="email" cssClass="text-secondary" />
					</app:form-group>

					<app:form-group cssClass="mb-3" label="Email">
						<form:input path="email" type="email" cssClass="form-control" placeholder="Enter Email Address" />
						<form:errors path="email" cssClass="text-secondary" />
					</app:form-group>
					
					<app:form-group cssClass="mb-3" label="Password">
						<form:input path="password" type="password" cssClass="form-control" placeholder="Enter Password" />
						<form:errors path="password" cssClass="text-secondary" />
					</app:form-group>
					
					<div>
						<button class="btn btn-outline-primary">
							<i class="bi-person-plus"></i> Sign Up
						</button>
						
						<a href="${root}/public/signin" class="btn btn-outline-primary">
							<i class="bi-unlock"></i> Sign In
						</a>
					</div>
				</form:form>
			</div>
		</div>
		
	</div>
</app:layout>