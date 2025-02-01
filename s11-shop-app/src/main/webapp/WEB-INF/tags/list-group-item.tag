<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="value" required="true" %>

<c:if test="${(value ne null) and (value ne '')}">
	<li class="list-group-item">
		<div class="fw-bold fs-6">
			${label}
		</div>
		${value}
	</li>
</c:if>
