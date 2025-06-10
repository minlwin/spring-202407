package com.jdc.portal.api.utils;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.jdc.portal.api.utils.exceptions.ApiBusinessException;

@Component
public class EntityOperationUtils {

	private static MessageSource messageSource;
	
	public EntityOperationUtils(MessageSource _messageSource) {
		messageSource = _messageSource;
	}

	public static <T, ID> T safeCall(Optional<T> optional, String entity, ID id) {
		return optional.orElseThrow(() -> new ApiBusinessException(messageSource.getMessage("app.business.entity.notfound", new Object[] {entity, id}, null)));
	}
}
