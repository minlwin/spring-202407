package com.jdc.portal.api.model.entity;

import java.util.List;

import com.jdc.portal.api.model.AbstractEntity;
import com.jdc.portal.api.model.dto.CourseContent;
import com.jdc.portal.api.model.entity.converter.CourseContentConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Course extends AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private Level level;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private String icon;
	@Column(columnDefinition = "TEXT")
	@Convert(converter = CourseContentConverter.class)
	private List<CourseContent> contents;
	
	public enum Level {
		Basic, Intermediate, Advance
	}
}
