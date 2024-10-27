package com.jdc.spring.model.repo;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.controller.input.RegionForm;
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
	
	@Transactional
	public Region create(RegionForm form) {
		
		var entity = new Region();
		entity.setName(form.getName());
		entity.setRegion(form.getRegion());
		entity.setCapital(form.getCapital());
		em.persist(entity);
		
		return entity;
	}
}
