package com.jdc.spring.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Registration {

	@EmbeddedId
	private RegistrationPk id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "section_id", insertable = false, updatable = false)
	private Section section;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "student_id", insertable = false, updatable = false)
	private Student student;
	
	@Column(nullable = false)
	private LocalDateTime registedAt;
	
	private String remark;
}
