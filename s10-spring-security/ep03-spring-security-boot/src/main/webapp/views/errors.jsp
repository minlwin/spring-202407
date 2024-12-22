<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<app:layout title="Error">

	<h3><i class="bi-info-circle"></i> Application Error</h3>
	
	<div class="alert alert-info">
		${message}
	</div>


</app:layout>