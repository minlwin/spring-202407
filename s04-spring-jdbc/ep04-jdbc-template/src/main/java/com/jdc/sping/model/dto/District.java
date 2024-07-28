package com.jdc.sping.model.dto;

public record District(
		int id, 
		String name,
		int divisionId,
		String divisionName,
		String region) {

}
