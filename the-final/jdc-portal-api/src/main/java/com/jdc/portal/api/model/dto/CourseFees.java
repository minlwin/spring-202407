package com.jdc.portal.api.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

@Data
@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	property = "type"
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = CourseFeesForMonthly.class, name = "Monthly"),
	@JsonSubTypes.Type(value = CourseFeesForTotal.class, name = "Total"),
})
public abstract class CourseFees {
	
	public enum Type {
		Monthly, Total
	}
	
	private Type type;

	public abstract BigDecimal getTotal();
}
