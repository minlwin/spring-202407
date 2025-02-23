package com.jdc.spring.location.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.spring.location.model.entity.Region;

public interface RegionRepo extends JpaRepository<Region, Integer>{

}
