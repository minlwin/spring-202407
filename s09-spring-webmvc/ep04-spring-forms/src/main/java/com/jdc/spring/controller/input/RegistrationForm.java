package com.jdc.spring.controller.input;

import java.time.LocalDate;

import com.jdc.spring.model.entity.Section;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistrationForm {
	
	private int sectionId;
	private String course;
	private LocalDate startAt;
	private int fees;
	
	@NotBlank(message = "Please enter student name.")
	private String name;
	@NotBlank(message = "Please enter phone number.")
	private String phone;
	@NotBlank(message = "Please enter email address.")
	private String email;
	
	private String remark;


	public static RegistrationForm from(Section entity) {
		var dto = new RegistrationForm();
		
		dto.setSectionId(entity.getId());
		dto.setCourse(entity.getCourse().getName());
		dto.setStartAt(entity.getStartDate());
		dto.setFees(entity.getFees());
		
		return dto;
	}
}
