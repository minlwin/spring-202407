package com.jdc.portal.api.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Account {

	@Id
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private Role role;
	private boolean activated;
	private LocalDate expiredAt;
	
	private LocalDateTime registeredAt;
	private LocalDateTime activatedAt;
	
	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
	private Employee employee;
	
	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
	private Student student;
	
	public enum Role {
		Admin, Student, Employee
	}
}
