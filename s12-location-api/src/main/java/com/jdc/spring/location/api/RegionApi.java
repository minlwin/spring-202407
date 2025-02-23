package com.jdc.spring.location.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.spring.location.model.entity.Region;
import com.jdc.spring.location.model.repo.RegionRepo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("regions")
public class RegionApi {
	
	private final RegionRepo repo;

	@GetMapping
	List<Region> findAll() {
		return repo.findAll();
	}
}
