package com.jdc.portal.api.model.dto;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseFeesForMonthly extends CourseFees {
	
	public CourseFeesForMonthly() {
		setType(Type.Monthly);
	}
	
	private BigDecimal registrationFees;
	private BigDecimal monthlyFees;
	private Integer months;

	@Override
	public BigDecimal getTotal() {
		var registration = Optional.ofNullable(registrationFees).orElse(BigDecimal.ZERO);
		var fees = Optional.ofNullable(monthlyFees).orElse(BigDecimal.ZERO);
		var monthValue = Optional.ofNullable(months).orElse(0);
		
		return fees.multiply(BigDecimal.valueOf(monthValue)).add(registration);
	}

}
