<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<app:layout title="Home">

	<h3><i class="bi-house"></i> Security Demo</h3>
	
	<c:if test="${not empty message}">
		<div class="alert alert-info">
			${message}
		</div>
	</c:if>
	
</app:layout>