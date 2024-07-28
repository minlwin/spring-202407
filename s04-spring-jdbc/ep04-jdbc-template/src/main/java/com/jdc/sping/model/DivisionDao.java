package com.jdc.sping.model;

import java.util.List;

import com.jdc.sping.model.dto.Division;
import com.jdc.sping.model.dto.DivisionForm;

public interface DivisionDao {

	int create(DivisionForm form);
	
	long countAll();
	
	Division findById(int id);

	List<Division> findByName(String name);
	List<Division> findByRegion(String region);
	List<Division> findByCapital(String name);
	
	boolean update(int id, DivisionForm form);
	
	boolean delete(int id);
	
}
