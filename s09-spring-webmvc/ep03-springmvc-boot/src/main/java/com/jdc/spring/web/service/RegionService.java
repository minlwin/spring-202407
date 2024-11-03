package com.jdc.spring.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.spring.web.controller.input.RegionSearch;
import com.jdc.spring.web.model.entity.Region;
import com.jdc.spring.web.model.entity.Region_;
import com.jdc.spring.web.model.repo.RegionRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;

@Service
public class RegionService {
	
	@Autowired
	private RegionRepo repo;

	public List<Region> search(RegionSearch search) {
		
		Function<CriteriaBuilder, CriteriaQuery<Region>> queryFunc = cb -> {
			var cq = cb.createQuery(Region.class);
			var root = cq.from(Region.class);
			cq.select(root);
			
			var params = new ArrayList<Predicate>();
			
			if(StringUtils.hasLength(search.region())) {
				// r.region like %region%
				params.add(cb.like(root.get(Region_.region), "%%%s%%".formatted(search.region())));
			}
			
			if(StringUtils.hasLength(search.name())) {
				// lower(r.name) like %name
				params.add(cb.like(cb.lower(root.get(Region_.name)), "%s%%".formatted(search.name().toLowerCase())));
			}

			if(StringUtils.hasLength(search.capital())) {
				// lower(r.capital) like %capital
				params.add(cb.like(cb.lower(root.get(Region_.capital)), "%s%%".formatted(search.capital().toLowerCase())));
			}
			
			cq.where(params.toArray(size -> new Predicate[size]));
			
			cq.orderBy(cb.asc(root.get(Region_.id)));

			return cq;
		};
		
		return repo.search(queryFunc);
	}

}
