package com.jdc.spring.model.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jdc.spring.model.entity.Region;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RegionRepo {

	@PersistenceContext
	private EntityManager em;
	
	public List<Region> findAll() {
		return em.createQuery("select r from Region r", Region.class).getResultList();
	}
	
	public Region findById(int id) {
		return em.find(Region.class, id);
	}

}
