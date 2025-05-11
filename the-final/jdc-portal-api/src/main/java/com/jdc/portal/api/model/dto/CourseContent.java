package com.jdc.portal.api.model.dto;

import java.util.List;

public record CourseContent(
		String title,
		String description,
		List<String> articles) {

}
