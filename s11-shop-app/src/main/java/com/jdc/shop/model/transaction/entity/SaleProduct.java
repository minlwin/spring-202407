package com.jdc.shop.model.transaction.entity;

import com.jdc.shop.model.master.entity.ProductStockHistory;
import com.jdc.shop.model.master.entity.ProductStockHistoryPk;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleProduct extends ProductStockHistory {

	public SaleProduct() {
		var pk = new ProductStockHistoryPk();
		pk.setAction(Action.Sell);
		setId(pk);
	}
	
	@ManyToOne(optional = false)
	private Sale sale;
	
	private int salePrice;
}
