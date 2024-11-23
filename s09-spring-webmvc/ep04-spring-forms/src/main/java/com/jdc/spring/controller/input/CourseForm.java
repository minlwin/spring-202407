package com.jdc.spring.controller.input;

import com.jdc.spring.model.entity.Course.Level;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseForm {

	private Integer id;
	
	@NotNull(message = "Please enter level.")
	private Level level;
	@NotBlank(message = "Please enter course name.")
	private String name;
	
	@Min(value = 100, message = "Please enter hours with min value 100.")
	@NotNull(message = "Please enter hours.")
	private Integer hours;
	
	@NotNull(message = "Please enter fees.")
	private Integer fees;
	
	private String description;

}
