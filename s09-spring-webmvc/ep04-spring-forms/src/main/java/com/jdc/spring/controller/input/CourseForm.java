package com.jdc.spring.controller.input;

import com.jdc.spring.model.entity.Course.Level;

import lombok.Data;

@Data
public class CourseForm {

	private Level level;
	private String name;
	private int hours;
	private int fees;
	private String description;

}
