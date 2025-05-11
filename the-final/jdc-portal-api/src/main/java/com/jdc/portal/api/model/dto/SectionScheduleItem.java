package com.jdc.portal.api.model.dto;

import java.time.DayOfWeek;

public record SectionScheduleItem(
		DayOfWeek day,
		String startTime,
		String endTime,
		String breakTime) {

	public String getType() {
		
		if(null != day) {
			return switch(day) {
			case SATURDAY, SUNDAY -> "Weekend";
			default -> "Week Day";
			};
		}
		
		return null;
	}
}
