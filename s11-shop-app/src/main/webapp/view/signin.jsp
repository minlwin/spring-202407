<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout title="Sign In">
	
	<div class="row">
		<div class="col d-flex align-items-center">
			<!-- Cover Photo -->
			<div class="me-5">
				<i class="bi-cart cover-icon"></i>
			</div>
			<div>
				<h1><i class="bi-unlock"></i> Sign In</h1>
				
				<p>
					Hi! There. You can search and buy our products from this site.
					If you want some other product that doesn't found in our site, you can request to us.
				</p>
				
				<p>
					You can feedback to our product. Thank you.
				</p>

				<form action="${root}/public/signin" class="w-50 mt-4" method="post">
					<sec:csrfInput/>
					
					<app:form-group cssClass="mb-3" label="Email">
						<input name="username" required="required" placeholder="Enter Email Addres" type="email" class="form-control" />
					</app:form-group>
					
					<app:form-group cssClass="mb-3" label="Password">
						<input name="password" required="required" placeholder="Enter Password" type="password" class="form-control" />
					</app:form-group>
					
					<div>
						<button class="btn btn-outline-primary">
							<i class="bi-unlock"></i> Sign In
						</button>
						
						<a href="${root}/public/signup" class="btn btn-outline-primary">
							<i class="bi-person-plus"></i> Sign Up
						</a>
					</div>
				</form>
			</div>
		</div>
		
	</div>
</app:layout>