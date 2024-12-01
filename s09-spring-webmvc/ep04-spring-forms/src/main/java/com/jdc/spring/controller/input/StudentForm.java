package com.jdc.spring.controller.input;

import com.jdc.spring.model.entity.Student.Education;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentForm {

	@NotNull(message = "Please select student.")
	private Integer id;
	
	@NotBlank(message = "Please enter student name.")
	private String name;
	
	@NotBlank(message = "Please enter student name.")
	private String phone;

	@NotBlank(message = "Please enter student name.")
	private String email;
	
	@NotBlank(message = "Please select last education.")
	private Education education;
	
	private String remark;
	
}
