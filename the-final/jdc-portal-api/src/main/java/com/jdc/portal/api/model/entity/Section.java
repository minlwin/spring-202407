package com.jdc.portal.api.model.entity;

import com.jdc.portal.api.model.AbstractEntity;
import com.jdc.portal.api.model.dto.CourseFees;
import com.jdc.portal.api.model.entity.converter.SectionFeesConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Section extends AbstractEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(optional = false)
	private Course course;
	@Column(nullable = false)
	private Type type;
	
	@Column(columnDefinition = "TEXT")
	@Convert(converter = SectionFeesConverter.class)
	private CourseFees fees;
	
	private boolean acceptClose;
	
	public enum Type {
		Campus, Online
	}
}
