package com.jdc.portal.api.model.entity;

import com.jdc.portal.api.model.dto.SectionSchedule;
import com.jdc.portal.api.model.entity.converter.SectionScheduleConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class SectionCampus extends Section implements SectionWithSchedule{

	@Column(columnDefinition = "TEXT")
	@Convert(converter = SectionScheduleConverter.class)
	private SectionSchedule schedule;
	
	private int seats;
	private int bufferSeats;
	
	public SectionCampus() {
		setType(Type.Campus);
	}
}
