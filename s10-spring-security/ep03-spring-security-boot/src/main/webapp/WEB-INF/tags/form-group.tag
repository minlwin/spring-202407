<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="label" required="true" type="java.lang.String" %>
<%@ attribute name="cssClass" type="java.lang.String" %>

<div class="mb-3 ${cssClass}">
	<label class="form-label">${label}</label>
	<jsp:doBody></jsp:doBody>
</div>