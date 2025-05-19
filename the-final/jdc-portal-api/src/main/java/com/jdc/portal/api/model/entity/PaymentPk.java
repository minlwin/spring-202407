package com.jdc.portal.api.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PaymentPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "section_id")
	private long sectionId;
	@Column(name = "student_id")
	private long studentId;
	private LocalDate pamentDate;
}
