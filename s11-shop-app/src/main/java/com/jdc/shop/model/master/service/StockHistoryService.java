package com.jdc.shop.model.master.service;

import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.output.StockChangeInfo;
import com.jdc.shop.model.PageInfo;
import com.jdc.shop.model.master.entity.ProductStockHistory;
import com.jdc.shop.model.master.entity.ProductStockHistory_;
import com.jdc.shop.model.master.entity.Product_;
import com.jdc.shop.model.master.repo.ProductStockHistoryRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockHistoryService {

	private final ProductStockHistoryRepo historyRepo;

	@Transactional(readOnly = true)
	public PageInfo<StockChangeInfo> search(int productId, int page, int size) {
		return historyRepo.search(queryFunc(productId), countFunc(productId), page, size);
	}

	private Function<CriteriaBuilder, CriteriaQuery<StockChangeInfo>> queryFunc(int productId) {
		return cb -> {
			var cq = cb.createQuery(StockChangeInfo.class);
			var root = cq.from(ProductStockHistory.class);
			
			StockChangeInfo.select(cb, cq, root);
			cq.where(where(cb, root, productId));
			
			return cq;
		};
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(int productId) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(ProductStockHistory.class);
			
			cq.select(cb.count(root));
			cq.where(where(cb, root, productId));
			
			return cq;
		};
	}

	private Expression<Boolean> where(CriteriaBuilder cb, Root<ProductStockHistory> root, int productId) {
		return cb.equal(root.get(ProductStockHistory_.product).get(Product_.id), productId);
	}

}
