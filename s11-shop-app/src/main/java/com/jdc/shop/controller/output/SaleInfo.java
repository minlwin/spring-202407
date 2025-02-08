package com.jdc.shop.controller.output;

import java.time.LocalDateTime;
import java.util.UUID;

import com.jdc.shop.model.account.entity.Account_;
import com.jdc.shop.model.account.entity.Customer_;
import com.jdc.shop.model.transaction.entity.Sale;
import com.jdc.shop.model.transaction.entity.Sale.Status;
import com.jdc.shop.model.transaction.entity.SalePk;
import com.jdc.shop.model.transaction.entity.SaleProduct_;
import com.jdc.shop.model.transaction.entity.Sale_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record SaleInfo(
		SalePk id,
		UUID customerId,
		String customerName,
		String customerPhone,
		String customerEmail,
		LocalDateTime saleAt,
		Status status,
		int items,
		int totals) {

	public static void select(CriteriaBuilder cb, CriteriaQuery<SaleInfo> cq, Root<Sale> root) {
		
		var items = root.join(Sale_.products, JoinType.LEFT);

		cq.multiselect(
			root.get(Sale_.id),
			root.get(Sale_.customer).get(Customer_.id),
			root.get(Sale_.customer).get(Customer_.name),
			root.get(Sale_.customer).get(Customer_.phone),
			root.get(Sale_.customer).get(Customer_.account).get(Account_.email),
			root.get(Sale_.saleAt),
			root.get(Sale_.status),
			cb.count(items),
			cb.sum(cb.prod(items.get(SaleProduct_.quantity), items.get(SaleProduct_.salePrice)))
		);
		
		cq.groupBy(
			root.get(Sale_.id),
			root.get(Sale_.customer).get(Customer_.id),
			root.get(Sale_.customer).get(Customer_.name),
			root.get(Sale_.customer).get(Customer_.phone),
			root.get(Sale_.customer).get(Customer_.account).get(Account_.email),
			root.get(Sale_.saleAt),
			root.get(Sale_.status)
		);
	}
}
