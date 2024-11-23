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
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private Level level;
	
	@Column(nullable = false)
	private String name;
	
	private int hours;
	private int fees;
	private String description;
	
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "course")
	private List<Section> sections;

	public enum Level {
		Basic, Intermediate, Advance
	}
}
