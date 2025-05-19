package com.jdc.portal.api.model.entity;

import java.util.Map;

import com.jdc.portal.api.model.entity.converter.IntegerMapConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class SectionOnlineVideo extends SectionOnline {
	
	@Column(columnDefinition = "TEXT")
	@Convert(converter = IntegerMapConverter.class)
	private Map<String, Integer> videoFiles;

	@Column(columnDefinition = "TEXT")
	@Convert(converter = IntegerMapConverter.class)
	private Map<String, Integer> hours;
	
	public SectionOnlineVideo() {
		setTeachingMethod(Teaching.Video);
	}
}
