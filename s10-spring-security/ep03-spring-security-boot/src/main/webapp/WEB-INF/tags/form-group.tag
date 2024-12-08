<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="label" required="true" type="java.lang.String" %>

<div class="mb-3">
	<label class="form-label">${label}</label>
	<jsp:doBody></jsp:doBody>
</div>