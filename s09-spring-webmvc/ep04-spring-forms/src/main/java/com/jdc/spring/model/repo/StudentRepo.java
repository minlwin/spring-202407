package com.jdc.spring.model.repo;

import java.util.Optional;

import com.jdc.spring.model.BaseRepository;
import com.jdc.spring.model.entity.Student;

public interface StudentRepo extends BaseRepository<Student, Integer>{

	Optional<Student> findOneByEmail(String email);
}
