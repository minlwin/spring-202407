package com.jdc.spring.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.controller.input.StudentForm;
import com.jdc.spring.controller.input.StudentSearch;
import com.jdc.spring.controller.output.StudentDetails;
import com.jdc.spring.controller.output.StudentInfo;
import com.jdc.spring.model.entity.Student;
import com.jdc.spring.model.entity.Student_;
import com.jdc.spring.model.repo.StudentRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class StudentService {
	
	@Autowired
	private StudentRepo repo;

	@Transactional(propagation = Propagation.MANDATORY)
	public Student save(String name, String phone, String email) {
		return repo.findOneByEmail(email)
				.orElseGet(() -> {
					var entity = new Student();
					entity.setName(name);
					entity.setPhone(phone);
					entity.setEmail(email);
					entity.setEntryAt(LocalDateTime.now());
					return repo.save(entity);
				});
	}

	public List<StudentInfo> search(StudentSearch search) {
		
		Function<CriteriaBuilder, CriteriaQuery<StudentInfo>> queryFunc = cb -> {
			var cq = cb.createQuery(StudentInfo.class);
			var root = cq.from(Student.class);
			StudentInfo.select(cq, root);
			cq.where(search.where(cb, root));
			cq.orderBy(cb.desc(root.get(Student_.entryAt)));
			return cq;
		};
		
		return repo.search(queryFunc);
	}

	public StudentDetails findById(int id) {
		return repo.findById(id).map(StudentDetails::from).orElseThrow();
	}

	public StudentForm findForEdit(int id) {
		return repo.findById(id).map(StudentForm::from).orElseThrow();
	}

	@Transactional
	public void save(StudentForm form) {
		var entity = repo.findById(form.getId()).orElseThrow();
		entity.setName(form.getName());
		entity.setPhone(form.getPhone());
		entity.setEmail(form.getEmail());
		entity.setLastEducation(form.getEducation());
		entity.setRemark(form.getRemark());		
	}

	public boolean findByEmail(String email) {
		return repo.findOneByEmail(email).isPresent();
	}

	public String findEmailById(Integer id) {
		return repo.findById(id).map(a -> a.getEmail()).orElseThrow();
	}

}
