package com.jdc.spring.controller.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.spring.model.entity.Course.Level;
import com.jdc.spring.model.entity.Course_;
import com.jdc.spring.model.entity.Registration;
import com.jdc.spring.model.entity.RegistrationPk;
import com.jdc.spring.model.entity.Registration_;
import com.jdc.spring.model.entity.Section_;
import com.jdc.spring.model.entity.Student_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record RegistrationInfo(
		RegistrationPk id,
		int courseId,
		Level level,
		String courseName,
		LocalDate startAt,
		int months,
		int fees,
		int seats,
		String studentName,
		String studentPhone,
		String studentEmail,
		LocalDateTime entryAt,
		LocalDateTime registedAt) {
	
	public static void select(CriteriaQuery<RegistrationInfo> cq, Root<Registration> root) {
		
		var section = root.join(Registration_.section);
		var course = section.join(Section_.course);
		var student = root.join(Registration_.student);
		
		cq.multiselect(
			root.get(Registration_.id),
			course.get(Course_.id),
			course.get(Course_.level),
			course.get(Course_.name),
			section.get(Section_.startDate),
			section.get(Section_.months),
			section.get(Section_.fees),
			section.get(Section_.availableSeats),
			student.get(Student_.name),
			student.get(Student_.phone),
			student.get(Student_.email),
			student.get(Student_.entryAt),
			root.get(Registration_.registedAt)
		);
	}
	
	public static RegistrationInfo from(Registration entity) {
		return builder()
				.id(entity.getId())
				.courseId(entity.getSection().getCourse().getId())
				.level(entity.getSection().getCourse().getLevel())
				.courseName(entity.getSection().getCourse().getName())
				.startAt(entity.getSection().getStartDate())
				.months(entity.getSection().getMonths())
				.fees(entity.getSection().getFees())
				.seats(entity.getSection().getAvailableSeats())
				.studentName(entity.getStudent().getName())
				.studentPhone(entity.getStudent().getPhone())
				.studentEmail(entity.getStudent().getEmail())
				.entryAt(entity.getStudent().getEntryAt())
				.registedAt(entity.getRegistedAt())
				.build();
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private RegistrationPk id;
		private int courseId;
		private Level level;
		private String courseName;
		private LocalDate startAt;
		private int months;
		private int fees;
		private int seats;
		private String studentName;
		private String studentPhone;
		private String studentEmail;
		private LocalDateTime entryAt;
		private LocalDateTime registedAt;
		
		private Builder() {
		}
		
		public RegistrationInfo build() {
			return new RegistrationInfo(id, courseId, level, courseName, startAt, months, fees, seats, studentName, studentPhone, studentEmail, entryAt, registedAt);
		}

		public Builder id(RegistrationPk id) {
			this.id = id;
			return this;
		}

		public Builder courseId(int courseId) {
			this.courseId = courseId;
			return this;
		}

		public Builder level(Level level) {
			this.level = level;
			return this;
		}

		public Builder courseName(String courseName) {
			this.courseName = courseName;
			return this;
		}

		public Builder startAt(LocalDate startAt) {
			this.startAt = startAt;
			return this;
		}

		public Builder months(int months) {
			this.months = months;
			return this;
		}

		public Builder fees(int fees) {
			this.fees = fees;
			return this;
		}

		public Builder seats(int seats) {
			this.seats = seats;
			return this;
		}

		public Builder studentName(String studentName) {
			this.studentName = studentName;
			return this;
		}

		public Builder studentPhone(String studentPhone) {
			this.studentPhone = studentPhone;
			return this;
		}

		public Builder studentEmail(String studentEmail) {
			this.studentEmail = studentEmail;
			return this;
		}

		public Builder entryAt(LocalDateTime entryAt) {
			this.entryAt = entryAt;
			return this;
		}

		public Builder registedAt(LocalDateTime registedAt) {
			this.registedAt = registedAt;
			return this;
		}
	}

}
