package com.jdc.spring.trx.model;

public class AccountException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private int historyId;

	public AccountException(String message) {
		super(message);
	}

	public AccountException(int historyId, String message) {
		super(message);
		this.historyId = historyId;
	}
	
	public int getHistoryId() {
		return historyId;
	}
}
