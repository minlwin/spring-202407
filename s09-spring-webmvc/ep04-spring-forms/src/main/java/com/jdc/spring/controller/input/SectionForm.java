package com.jdc.spring.controller.input;

import java.time.LocalDate;

import com.jdc.spring.model.entity.Course;
import com.jdc.spring.model.entity.Course.Level;
import com.jdc.spring.model.entity.Section;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SectionForm {

	private Integer id;
	private int courseId;
	private String courseName;
	private Level courseLevel;
	private int fees;
	
	@NotNull(message = "Please enter start date.")
	private LocalDate startDate;
	
	@NotNull(message = "Please enter months.")
	private Integer months;
	
	@NotNull(message = "Please enter available seats.")
	private Integer availableSeats;
	
	private String remark;

	public static SectionForm from(Section section) {
		var form = new SectionForm();
		form.setId(section.getId());
		form.setCourseId(section.getCourse().getId());
		form.setCourseName(section.getCourse().getName());
		form.setCourseLevel(section.getCourse().getLevel());
		form.setStartDate(section.getStartDate());
		form.setMonths(section.getMonths());
		form.setAvailableSeats(section.getAvailableSeats());
		form.setRemark(section.getRemark());
		return form;
	}

	public static SectionForm from(Course course) {
		var form = new SectionForm();

		form.setCourseId(course.getId());
		form.setCourseName(course.getName());
		form.setCourseLevel(course.getLevel());
		
		return form;
	}

}
