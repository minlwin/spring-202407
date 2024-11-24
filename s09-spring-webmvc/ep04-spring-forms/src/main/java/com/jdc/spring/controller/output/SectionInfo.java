package com.jdc.spring.controller.output;

import java.time.LocalDate;

import com.jdc.spring.model.entity.Course.Level;
import com.jdc.spring.model.entity.Course_;
import com.jdc.spring.model.entity.Registration_;
import com.jdc.spring.model.entity.Section;
import com.jdc.spring.model.entity.Section_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record SectionInfo(
		int id,
		Level level,
		String courseName,
		LocalDate startAt,
		int months,
		int fees,
		int seats,
		long registrations) {
	
	public static void select(CriteriaBuilder cb, CriteriaQuery<SectionInfo> cq, Root<Section> root) {
		
		var registration = root.join(Section_.registrations, JoinType.LEFT);
		
		cq.multiselect(
			root.get(Section_.id),
			root.get(Section_.course).get(Course_.level),
			root.get(Section_.course).get(Course_.name),
			root.get(Section_.startDate),
			root.get(Section_.months),
			root.get(Section_.fees),
			root.get(Section_.availableSeats),
			cb.count(registration.get(Registration_.id))
		);
		
		cq.groupBy(
			root.get(Section_.id),
			root.get(Section_.course).get(Course_.level),
			root.get(Section_.course).get(Course_.name),
			root.get(Section_.startDate),
			root.get(Section_.months),
			root.get(Section_.fees),
			root.get(Section_.availableSeats)
		);
	}

	public static SectionInfo from(Section entity) {
		return new SectionInfo(entity.getId(), entity.getCourse().getLevel(), entity.getCourse().getName(), entity.getStartDate(), entity.getMonths(), entity.getFees(), entity.getAvailableSeats(), entity.getRegistrations().size());
	}
}
