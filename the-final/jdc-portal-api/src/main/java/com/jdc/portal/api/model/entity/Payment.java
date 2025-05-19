package com.jdc.portal.api.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jdc.portal.api.model.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Payment extends AbstractEntity{

	@EmbeddedId
	private PaymentPk id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "section_id", referencedColumnName = "section_id", insertable = false, updatable = false)
	@JoinColumn(name = "student_id", referencedColumnName = "student_id", insertable = false, updatable = false)
	private Registration registration;
	
	@Column(nullable = false)
	private BigDecimal amount;
	@Column(nullable = false)
	private LocalDateTime pamentAt;
	
	@Column(nullable = false)
	private Type type;
	private String screenShot;

	private boolean confirmed;
	private LocalDateTime confirmedAt;
	
	public enum Type {
		Campus, Online
	}
}
