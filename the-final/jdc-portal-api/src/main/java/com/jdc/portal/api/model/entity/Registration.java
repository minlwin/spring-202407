package com.jdc.portal.api.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jdc.portal.api.model.AbstractEntity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Registration extends AbstractEntity {

	@EmbeddedId
	private RegistrationPk id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "section_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Section section;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Student student;
	
	private LocalDateTime registeredAt;
	private BigDecimal registrationFees;
	private BigDecimal totalFees;
	private BigDecimal discount;
	private BigDecimal totalPaid;
	
	private Status status;
	private LocalDateTime stateChangeAt;
	private String statusChangeReason;
	
	public enum Status {
		Valid, Suspend, Leaved
	}
}
