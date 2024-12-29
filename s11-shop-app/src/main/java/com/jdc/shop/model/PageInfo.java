package com.jdc.shop.model;

import java.util.List;

public record PageInfo<T>(
		List<T> contents,
		long count,
		int page,
		int size
		) {

}
