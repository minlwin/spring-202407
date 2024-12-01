<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="label" type="java.lang.String" required="true" %>
<%@ attribute name="value" required="true" %>

<div class="row mb-2">
	<div class="col-4">
		<div class="d-flex justify-content-between">
			<span>${label}</span>
			<span>:</span>
		</div>
	</div>
	
	<div class="col">
		<span>${value}</span>
	</div>
</div>