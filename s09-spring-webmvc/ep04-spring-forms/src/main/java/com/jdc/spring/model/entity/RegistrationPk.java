package com.jdc.spring.model.entity;

import org.springframework.util.StringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationPk {

	@Column(name = "section_id")
	private int sectionId;
	
	@Column(name = "student_id")
	private int studentId;
	
	public String getCode() {
		return "%04d-%04d".formatted(sectionId, studentId);
	}
	
	public static RegistrationPk from(String code) {
		if(StringUtils.hasLength(code)) {
			var array = code.split("-");
			return new RegistrationPk(
					Integer.parseInt(array[0]), 
					Integer.parseInt(array[1]));
		}
		
		return null;
	}
}
