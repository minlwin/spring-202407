package com.jdc.portal.api.model.entity;

import java.time.LocalDate;

import com.jdc.portal.api.model.AbstractEntity;
import com.jdc.portal.api.model.dto.Contact;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Student extends AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String name;
	private LocalDate dob;
	private Gender gender;
	
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	private Account account;
	
	@Embedded
	private Contact contact;
	
	private LastEducation education;
	private String major;
	
	public enum Gender {
		Male, Female
	}
	
	public enum LastEducation {
		BEHS, College, Master, Other
	}
}
