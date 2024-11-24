package com.jdc.spring.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.controller.input.SectionForm;
import com.jdc.spring.controller.input.SectionSearch;
import com.jdc.spring.controller.output.SectionDetails;
import com.jdc.spring.controller.output.SectionInfo;
import com.jdc.spring.model.entity.Section;
import com.jdc.spring.model.entity.Section_;
import com.jdc.spring.model.repo.CourseRepo;
import com.jdc.spring.model.repo.SectionRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SectionService {

	private final SectionRepo sectionRepo;
	private final CourseRepo courseRepo;
	
	@Transactional(readOnly = true)
	public List<SectionInfo> search(SectionSearch search) {
		
		Function<CriteriaBuilder, CriteriaQuery<SectionInfo>> queryFunc = cb -> {
			var cq = cb.createQuery(SectionInfo.class);
			var root = cq.from(Section.class);
			
			SectionInfo.select(cb, cq, root);
			cq.where(search.where(cb, root));
			
			cq.orderBy(cb.desc(root.get(Section_.startDate)));
			
			return cq;
		};
		
		return sectionRepo.search(queryFunc);
	}

	@Transactional(readOnly = true)
	public SectionDetails findById(int id) {
		var entity = sectionRepo.findById(id).orElseThrow();
		return SectionDetails.from(entity);
	}

	@Transactional
	public void save(SectionForm form) {
		
		var course = courseRepo.findById(form.getCourseId()).orElseThrow();
		
		var entity = (null != form.getId()) ? sectionRepo.findById(form.getId()).orElseThrow() : new Section();
		
		entity.setCourse(course);
		entity.setStartDate(form.getStartDate());
		entity.setFees(course.getFees());
		entity.setMonths(form.getMonths());
		entity.setAvailableSeats(form.getAvailableSeats());
		entity.setRemark(form.getRemark());
		
		sectionRepo.save(entity);
	}

	@Transactional(readOnly = true)
	public SectionForm findForEdit(Integer id, Integer courseId) {
		
		if(null != id) {
			var section = sectionRepo.findById(id).orElseThrow();
			return SectionForm.from(section);
		}
		
		var course = courseRepo.findById(courseId).orElseThrow();
		return SectionForm.from(course);
	}

}
