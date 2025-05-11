package com.jdc.portal.api.model.entity;

import com.jdc.portal.api.model.dto.SectionSchedule;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class SectionCampus extends Section implements SectionWithSchedule{

	private SectionSchedule schedule;
}
