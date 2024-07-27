package com.jdc.spring.jdbc;

import java.util.List;

import com.jdc.spring.jdbc.model.Division;

public interface DivisionDao {

	List<Division> findAll();
}
