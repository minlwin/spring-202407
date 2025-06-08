package com.jdc.portal.api.utils.exceptions;

import java.util.List;

public class ApiValidationException extends ApiBusinessException{

	private static final long serialVersionUID = 1L;

	public ApiValidationException(List<String> messages) {
		super(messages);
	}

}
