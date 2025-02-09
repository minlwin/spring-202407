<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout title="Products" menu="product" group="master">
    
	<app:page-title title="Edit Product" icon="bi-pencil" />
    
    <div class="row mt-3">
    	<div class="col">
    		<app:product-images 
    			coverPhoto="${details.coverPhoto}" 
    			photos="${details.photos}" />
    	</div>    	

    	<div class="col">

    		<sf:form method="post" modelAttribute="form">

    			<app:form-group label="Name" cssClass="mb-3">
    				<sf:input path="name" cssClass="form-control" placeholder="Enter Product Name"/>
    				<sf:errors path="name" cssClass="text-secondary" />
    			</app:form-group>
    			
    			<app:form-group label="Sell Price" cssClass="mb-3">
    				<sf:input path="salePrice" cssClass="form-control" type="number"/>
    				<sf:errors path="salePrice" cssClass="text-secondary" />
    			</app:form-group>

    			<app:form-group label="Description" cssClass="mb-3">
					<sf:textarea path="description" cssClass="form-control"/>
    			</app:form-group>
				
				<div class="row">
					<div class="col-4">
						<label class="form-label">Name</label>
					</div>
					<div class="col">
						<label class="form-label">Feature</label>
					</div>
				</div>

				<section id="featuresHolder" class="mb-3">
					
					<c:forEach var="item" varStatus="status" items="${form.features}">
						<div class="row ${status.index gt 0 ? 'mt-2' : ''}">
							
							<sf:hidden path="features[${status.index}].deleted" data-server="true" />
							
							<div class="col-4">
								<sf:input path="features[${status.index}].name" cssClass="form-control" placeholder="Enter Name"/>
								<sf:errors path="features[${status.index}].name" cssClass="text-secondary" />
							</div>
							
							<div class="col">
								<div class="input-group">
									<sf:input path="features[${status.index}].feature" cssClass="form-control" placeholder="Enter Feature"/>
									<button type="button" class="btn btn-outline-primary input-group-text deleteBtn">
										<i class="bi-trash"></i>
									</button>
								</div>
								<sf:errors path="features[${status.index}].feature" cssClass="text-secondary" />
							</div>
						</div>
					</c:forEach>
				</section>
				
				<div>
					<button id="addFeatureBtn" type="button" class="btn btn-outline-primary">
						<i class="bi-plus"></i> Add New Feature
					</button>
					
					<button type="submit" class="btn btn-primary">
						<i class="bi-save"></i> Save Product
					</button>
				</div>
    		</sf:form>
    	</div>

    </div>
    
    <script type="text/javascript" src="${root}/resources/js/product-edit.js"></script>
    
</app:layout>    