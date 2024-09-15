package com.jdc.transaction.service.model;

public record SaleResult(int id, Status status, String message) {

	public enum Status {
		Initiated, Success, Error
	}
}
