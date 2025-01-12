<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="label" required="true" %>
<%@ attribute name="cssClass" required="false" %>

<div class="input-group ${cssClass}">
	<label class="input-group-text">${label}</label>
	<jsp:doBody></jsp:doBody>
</div>	
