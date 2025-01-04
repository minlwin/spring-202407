<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ attribute name="title" type="java.lang.String" required="true" %>
<%@ attribute name="icon" type="java.lang.String" %>

<h4>
<c:if test="${null ne icon}"><i class="${icon}"></i></c:if> ${title}
</h4>