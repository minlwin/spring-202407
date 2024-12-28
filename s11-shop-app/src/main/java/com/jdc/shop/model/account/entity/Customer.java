package com.jdc.shop.model.account.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.jdc.shop.model.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Customer extends AbstractEntity{

	@Id
	private UUID id;
	
	@MapsId
	@OneToOne(optional = false)
	private Account account;
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String phone;

	private String profileImage;
	private LocalDate dob;
	private Gender gender;
	
	private LocalDateTime registeredAt;

	public enum Gender {
		Male, Female
	}
}
