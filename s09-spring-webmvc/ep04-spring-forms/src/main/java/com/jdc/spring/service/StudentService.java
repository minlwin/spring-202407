package com.jdc.spring.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.controller.input.StudentForm;
import com.jdc.spring.controller.input.StudentSearch;
import com.jdc.spring.controller.output.StudentDetails;
import com.jdc.spring.controller.output.StudentInfo;
import com.jdc.spring.model.entity.Student;
import com.jdc.spring.model.repo.StudentRepo;

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
		// TODO Auto-generated method stub
		return null;
	}

	public StudentDetails findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public StudentForm findForEdit(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void save(StudentForm form) {
		// TODO Auto-generated method stub
		
	}

}
