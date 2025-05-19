package com.jdc.portal.api.model.dto;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseFeesForTotal extends CourseFees{

	public CourseFeesForTotal() {
		setType(Type.Total);
	}
	
	private BigDecimal registrationFees;
	private BigDecimal courseFees;
	
	@Override
	public BigDecimal getTotal() {
		var registration = Optional.ofNullable(registrationFees).orElse(BigDecimal.ZERO);
		var course = Optional.ofNullable(courseFees).orElse(BigDecimal.ZERO);
		return registration.add(course);
	}
}
