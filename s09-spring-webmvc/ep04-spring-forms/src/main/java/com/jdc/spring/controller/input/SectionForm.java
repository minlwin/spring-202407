package com.jdc.spring.controller.input;

import java.time.LocalDate;

import com.jdc.spring.model.entity.Course.Level;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SectionForm {

	private Integer id;
	private int courseId;
	private int courseName;
	private Level courseLevel;
	private int fees;
	
	@NotNull(message = "Please enter start date.")
	private LocalDate startDate;
	@NotNull(message = "Please enter months.")
	private Integer months;
	@NotNull(message = "Please enter available seats.")
	private Integer availableSeats;
	private String remark;

}
