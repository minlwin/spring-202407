<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ attribute name="photos" type="java.util.List<java.lang.String>" %>
<%@ attribute name="coverPhoto" %>

<!-- Photos -->
<div class="d-flex">
	<!-- Photo List -->				
	<c:if test="${not empty photos}">
		<div class="image-control">
			<c:forEach var="photo" items="${photos}">
				<img class="image-fluid image-control-image mb-2" src="${root}/resources/images/${photo}" alt="" />
			</c:forEach>
		</div>
	</c:if>	
	<!-- Cover Photo -->
	<div class="flex-fill">
		<c:choose>
			<c:when test="${coverPhoto ne null and coverPhoto ne ''}">
				<div class="ps-2">
					<img id="coverPhoto" class="image-fluid product-cover-image" src="${root}/resources/images/${coverPhoto}" alt="" />
				</div>
			</c:when>
			<c:otherwise>
				<img class="image-fluid product-cover-image" src="${root}/resources/photo/default.jpg">
			</c:otherwise>
		</c:choose>
	</div>	
	
	<script type="text/javascript" src="${root}/resources/js/product-images.js"></script>	
</div>
