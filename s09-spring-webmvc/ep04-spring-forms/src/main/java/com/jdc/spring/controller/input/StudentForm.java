package com.jdc.spring.controller.input;

import com.jdc.spring.model.entity.Student;
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
	
	@NotNull(message = "Please select last education.")
	private Education education;
	
	private String remark;
	
	public static StudentForm from(Student entity) {
		var form = new StudentForm();
		
		form.setId(entity.getId());
		form.setName(entity.getName());
		form.setPhone(entity.getPhone());
		form.setEmail(entity.getEmail());
		form.setEducation(entity.getLastEducation());
		form.setRemark(entity.getRemark());
		
		return form;
	}
	
}
