package com.jdc.portal.api.model.dto;

import java.util.List;

public record PageResult<T>(
		List<T> contents,
		Integer page,
		Integer size,
		Long count) {

	public static<R> PageResult<R> of(Long count, int page, int size) {
		return new PageResult<R>(
			List.of(),
			page,
			size,
			count
		);
	}

}
