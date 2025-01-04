package com.jdc.shop.model.master.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.input.CategorySearch;
import com.jdc.shop.controller.output.CategoryInfo;
import com.jdc.shop.model.master.entity.Category;
import com.jdc.shop.model.master.repo.CategoryRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryManagementService {
	
	private final CategoryRepo repo;

	@Transactional(readOnly = true)
	public List<CategoryInfo> search(CategorySearch search) {
		return repo.search(searchFunc(search));
	}

	private Function<CriteriaBuilder, CriteriaQuery<CategoryInfo>> searchFunc(CategorySearch search) {
		return cb -> {
			var cq = cb.createQuery(CategoryInfo.class);
			var root = cq.from(Category.class);
			
			CategoryInfo.select(cb, cq, root);
			cq.where(search.where(cb, root));
			
			return cq;
		};
	}

}
