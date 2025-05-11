package com.jdc.portal.api.utils.exceptions;

import java.util.ArrayList;
import java.util.List;

public abstract class ApiBaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private List<String> messages;

	public ApiBaseException(Throwable cause) {
		super(cause);
		messages = new ArrayList<>();
		messages.add(cause.getMessage());
	}
	
	public ApiBaseException(Throwable cause, String message) {
		super(cause);
		messages = new ArrayList<>();
		messages.add(message);
	}
	
	public ApiBaseException(String message) {
		messages = new ArrayList<>();
		messages.add(message);
	}
	
	public ApiBaseException(List<String> messages) {
		this.messages = messages;
	}
	
	public List<String> getMessages() {
		return messages;
	}
}
