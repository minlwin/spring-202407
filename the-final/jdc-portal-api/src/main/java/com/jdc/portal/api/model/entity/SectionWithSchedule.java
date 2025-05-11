package com.jdc.portal.api.model.entity;

import com.jdc.portal.api.model.dto.SectionSchedule;

public interface SectionWithSchedule {

	SectionSchedule getSchedule();
	void setSchedule(SectionSchedule schedule);
}
