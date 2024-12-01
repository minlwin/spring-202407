package com.jdc.spring.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String phone;

	@Column(nullable = false, unique = true)
	private String email;
	
	private Education lastEducation;
	
	@Column(nullable = false)
	private LocalDateTime entryAt;
	
	private String remark;
	
	@OneToMany(mappedBy = "student")
	private List<Registration> registrations;
	
	public enum Education {
		BEHS, College, Master
	}
}
