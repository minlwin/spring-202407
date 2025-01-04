package com.jdc.shop.controller.output;

import java.time.LocalDateTime;

import com.jdc.shop.model.master.entity.Category;
import com.jdc.shop.model.master.entity.Category_;
import com.jdc.shop.model.master.entity.Product_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record CategoryInfo(
		int id,
		String name,
		LocalDateTime createdAt,
		String createdBy,
		LocalDateTime updatedAt,
		String updatedBy,
		long products) {

	public static void select(CriteriaBuilder cb, CriteriaQuery<CategoryInfo> cq, Root<Category> root) {
		
		var product = root.join(Category_.products, JoinType.LEFT);
		
		cq.multiselect(
			root.get(Category_.id),
			root.get(Category_.name),
			root.get(Category_.createdAt),
			root.get(Category_.createdBy),
			root.get(Category_.updatedAt),
			root.get(Category_.updatedBy),
			cb.count(product.get(Product_.id))
		);
		
		cq.groupBy(
			root.get(Category_.id),
			root.get(Category_.name),
			root.get(Category_.createdAt),
			root.get(Category_.createdBy),
			root.get(Category_.updatedAt),
			root.get(Category_.updatedBy)
		);
	}
}
