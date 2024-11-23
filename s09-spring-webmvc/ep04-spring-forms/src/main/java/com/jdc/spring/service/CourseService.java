package com.jdc.spring.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.controller.input.CourseForm;
import com.jdc.spring.controller.input.CourseSearch;
import com.jdc.spring.controller.output.CourseDetails;
import com.jdc.spring.controller.output.CourseInfo;
import com.jdc.spring.model.entity.Course;
import com.jdc.spring.model.entity.Course_;
import com.jdc.spring.model.repo.CourseRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {

	private final CourseRepo repo;

	@Transactional(readOnly = true)
	public List<CourseInfo> search(CourseSearch search) {
		
		Function<CriteriaBuilder, CriteriaQuery<CourseInfo>> queryFunc = cb -> {
			var cq = cb.createQuery(CourseInfo.class);
			var root = cq.from(Course.class);
			
			CourseInfo.select(cb, cq, root);
			cq.where(search.where(cb, root));
			
			cq.orderBy(cb.desc(root.get(Course_.createdAt)));
			
			return cq;
		};
		
		return repo.search(queryFunc);
	}

	@Transactional
	public void save(CourseForm form) {

		var course = (form.getId() != null) ? 
				repo.getReferenceById(form.getId()) : new Course();
		course.setName(form.getName());
		course.setLevel(form.getLevel());
		course.setHours(form.getHours());
		course.setFees(form.getFees());
		course.setDescription(form.getDescription());
		course.setCreatedAt(LocalDateTime.now());
		
		repo.save(course);
	}
	
	@Transactional(readOnly = true)
	public CourseDetails findById(int id) {
		
		var course = repo.findById(id)
				.orElseThrow();
		
		return CourseDetails.from(course);
	}

	@Transactional(readOnly = true)
	public CourseForm findForEdit(Integer id) {
		var course = repo.findById(id)
				.orElseThrow();
		
		var form = new CourseForm();
		form.setId(course.getId());
		form.setName(course.getName());
		form.setLevel(course.getLevel());
		form.setFees(course.getFees());
		form.setHours(course.getHours());
		form.setDescription(course.getDescription());
		
		return form;
	}
}
