package com.jdc.shop.model.transaction.service;

import static com.jdc.shop.utils.EntityOperationUtils.safeCall;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.shop.controller.input.SaleSearch;
import com.jdc.shop.controller.output.SaleDetails;
import com.jdc.shop.controller.output.SaleInfo;
import com.jdc.shop.model.PageInfo;
import com.jdc.shop.model.transaction.entity.Sale;
import com.jdc.shop.model.transaction.entity.SalePk;
import com.jdc.shop.model.transaction.entity.Sale_;
import com.jdc.shop.model.transaction.repo.SaleRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService implements InvoiceService{
	
	private final SaleRepo saleRepo;

	@Override
	public SaleDetails findById(String id) {
		return safeCall(Optional.ofNullable(id)
				.filter(StringUtils::hasLength)
				.map(SalePk::parse)
				.flatMap(saleRepo::findById)
				.map(SaleDetails::from), "Sale", "id", id);
	}

	@Override
	public PageInfo<SaleInfo> search(SaleSearch search, int page, int size) {
		return saleRepo.search(queryFunc(search), countFunc(search), page, size);
	}

	private Function<CriteriaBuilder, CriteriaQuery<SaleInfo>> queryFunc(SaleSearch search) {
		return cb -> {
			var cq = cb.createQuery(SaleInfo.class);
			var root = cq.from(Sale.class);
			
			SaleInfo.select(cb, cq, root);
			cq.where(search.where(cb, root));
			
			cq.orderBy(cb.desc(root.get(Sale_.saleAt)));
			
			return cq;
		};
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(SaleSearch search) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Sale.class);
			cq.select(cb.count(root.get(Sale_.id)));
			cq.where(search.where(cb, root));
			return cq;
		};
	}	

}
