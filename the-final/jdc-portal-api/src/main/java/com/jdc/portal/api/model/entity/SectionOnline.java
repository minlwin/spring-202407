package com.jdc.portal.api.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.JOINED)
public class SectionOnline extends Section {
	
	private Teaching teachingMethod;

	public enum Teaching {
		Zoom, Video
	}
}
