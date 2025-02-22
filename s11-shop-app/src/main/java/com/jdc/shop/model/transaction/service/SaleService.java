package com.jdc.shop.model.transaction.service;

import static com.jdc.shop.utils.EntityOperationUtils.safeCall;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.shop.controller.input.SaleSearch;
import com.jdc.shop.controller.output.SaleDetails;
import com.jdc.shop.controller.output.SaleInfo;
import com.jdc.shop.model.BusinessException;
import com.jdc.shop.model.PageInfo;
import com.jdc.shop.model.account.entity.Account.Role;
import com.jdc.shop.model.account.repo.AccountRepo;
import com.jdc.shop.model.transaction.entity.Cancel;
import com.jdc.shop.model.transaction.entity.CancelPk;
import com.jdc.shop.model.transaction.entity.CancelProduct;
import com.jdc.shop.model.transaction.entity.Sale;
import com.jdc.shop.model.transaction.entity.Sale.Status;
import com.jdc.shop.model.transaction.entity.SalePk;
import com.jdc.shop.model.transaction.entity.Sale_;
import com.jdc.shop.model.transaction.repo.CancelProductRepo;
import com.jdc.shop.model.transaction.repo.CancelRepo;
import com.jdc.shop.model.transaction.repo.SaleRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService implements InvoiceService{
	
	private final SaleRepo saleRepo;
	private final AccountRepo accountRepo;
	private final CancelRepo cancelRepo;
	private final CancelProductRepo cancelProductRepo;

	@Override
	@Transactional(readOnly = true)
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

	@Override
	@Transactional
	public void cancel(String id, String reason) {
		
		// Get Login User
		var loginUser = safeCall(Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
				.map(a -> a.getName())
				.flatMap(a -> accountRepo.findOneByEmail(a)), "Account", "login id", id);
		
		// Get Sale
		var sale = safeCall(Optional.ofNullable(id)
				.filter(StringUtils::hasLength)
				.map(SalePk::parse)
				.flatMap(saleRepo::findById), "Sale", "id", id);
		
		// Update Status
		if(sale.getStatus() != Status.Invoiced) {
			throw new BusinessException("%s sale can't be cancel.".formatted(sale.getStatus()));
		}
		
		// Create Cancel Transaction
		var cancel = new Cancel();
		cancel.setId(new CancelPk(sale.getId().getIssueAt(), sale.getId().getSeqNumber()));
		cancel.setCanceldBy(loginUser.getRole() == Role.Admin ? "Admin" : sale.getCustomer().getName());
		cancel.setCancledAt(LocalDateTime.now());
		
		cancel = cancelRepo.saveAndFlush(cancel);
		
		// Create Cancel Products for Stock History
		for(var item : sale.getProducts()) {
			
			var cancelItem = new CancelProduct();
			cancelItem.getId().setStockAction(cancel.getId());
			cancelItem.setProduct(item.getProduct());
			
			var beforeStock = item.getProduct().getStock().getStock();
			
			cancelItem.setBeforeStock(beforeStock);
			cancelItem.setQuantity(item.getQuantity());
			
			cancelProductRepo.saveAndFlush(cancelItem);
			
			// Update Stocks
			var stock = item.getProduct().getStock();
			stock.setStock(beforeStock + item.getQuantity());
		}

		sale.setStatus(Status.Cancel);
		sale.setSatusChangeAt(LocalDateTime.now());
		sale.setRemark(reason);
		
	}	

}
