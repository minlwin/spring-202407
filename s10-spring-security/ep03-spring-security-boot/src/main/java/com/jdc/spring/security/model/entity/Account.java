package com.jdc.spring.security.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(indexes = {
	@Index(name = "account-nameIndex", columnList = "name"),
	@Index(name = "account-requestAtIndex", columnList = "requested_at"),
	@Index(name = "account-statusIndex", columnList = "activated")
})
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Role role;
	
	private boolean activated;

	@Column(name = "requested_at")
	private LocalDateTime requestedAt;
	private LocalDateTime activatedAt;
	
	public enum Role {
		Admin, Member
	}
}
