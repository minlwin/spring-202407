package com.jdc.shop.controller.output;

import com.jdc.shop.model.account.entity.Customer_;
import com.jdc.shop.model.account.entity.Supplier_;
import com.jdc.shop.model.master.entity.ProductStockHistory;
import com.jdc.shop.model.master.entity.ProductStockHistory.Action;
import com.jdc.shop.model.master.entity.ProductStockHistoryPk;
import com.jdc.shop.model.master.entity.ProductStockHistoryPk_;
import com.jdc.shop.model.master.entity.ProductStockHistory_;
import com.jdc.shop.model.transaction.entity.PurchaseProduct;
import com.jdc.shop.model.transaction.entity.PurchaseProduct_;
import com.jdc.shop.model.transaction.entity.Purchase_;
import com.jdc.shop.model.transaction.entity.SaleProduct;
import com.jdc.shop.model.transaction.entity.SaleProduct_;
import com.jdc.shop.model.transaction.entity.Sale_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record StockChangeInfo(
		ProductStockHistoryPk id,
		int beforeStock, 
		int quantity,
		String remark,
		String target) {
	
	public int getStock() {
		return id.getAction() == Action.Buy ? beforeStock + quantity : beforeStock - quantity;
	}

	public static void select(
			CriteriaBuilder cb, 
			CriteriaQuery<StockChangeInfo> cq,
			Root<ProductStockHistory> root) {
		
		var purchaseProduct = cb.treat(root, PurchaseProduct.class);
		var purchase = purchaseProduct.join(PurchaseProduct_.purchase, JoinType.LEFT);
		var supplier = purchase.join(Purchase_.supplier, JoinType.LEFT);

		var saleProduct = cb.treat(root, SaleProduct.class);
		var sale = saleProduct.join(SaleProduct_.sale, JoinType.LEFT);
		var customer = sale.join(Sale_.customer, JoinType.LEFT);
		
		cq.multiselect(
			root.get(ProductStockHistory_.id),
			root.get(ProductStockHistory_.beforeStock),
			root.get(ProductStockHistory_.quantity),
			root.get(ProductStockHistory_.remark),
			cb.selectCase()
				.when(
					cb.equal(root.get(ProductStockHistory_.id).get(ProductStockHistoryPk_.action), Action.Buy), 
					supplier.get(Supplier_.shopName))
				.when(
					cb.equal(root.get(ProductStockHistory_.id).get(ProductStockHistoryPk_.action), Action.Sell), 
					customer.get(Customer_.name))
			
		);
	}
}
