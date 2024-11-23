package com.jdc.spring.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Section {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Course course;
	
	@Column(nullable = false)
	private int fees;

	@Column(nullable = false)
	private LocalDate startDate;
	
	@Column(nullable = false)
	private int months;

	@Column(nullable = false)
	private int availableSeats;
	
	private String remark;
}
