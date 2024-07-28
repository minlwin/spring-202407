package com.jdc.sping.model;

import java.util.List;

import com.jdc.sping.model.dto.District;
import com.jdc.sping.model.dto.DistrictForm;

public interface DistrictDao {

	int create(DistrictForm form);
	
	District findById(int id);
	
	long countAll();

	List<District> search(Integer divisionId, String region, String name);

	boolean update(int id, DistrictForm form);

	boolean delete(int id);
}
