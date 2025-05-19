package com.jdc.portal.api.model.dto;

import java.time.LocalDate;
import java.util.List;

public record SectionSchedule(
		LocalDate startDate,
		List<SectionScheduleItem> schedules) {

	public String getType() {
		
		if(null != schedules && !schedules.isEmpty()) {
			var types = schedules.stream()
					.map(a -> a.getType())
					.filter(a -> a != null)
					.distinct().toList();
			
			if(types.size() == 1) {
				return types.get(0);
			}
			
			if(types.size() == 2) {
				return "Mix Days";
			}
		}
		
		return null;
	}
}
