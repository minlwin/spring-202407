package com.jdc.spring.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.model.entity.Student;
import com.jdc.spring.model.repo.StudentRepo;

@Service
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

}
