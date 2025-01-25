package com.jdc.shop.utils;

import java.util.Optional;

import com.jdc.shop.model.BusinessException;

public class EntityOperationUtils {

	public static <T, ID> T safeCall(Optional<T> optional, String domain, String keyName, ID keyValue) {
		return optional.orElseThrow(() -> new BusinessException("There is no %s with %s %s."
				.formatted(domain, keyName, keyValue)));
	}
}
