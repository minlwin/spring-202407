package com.jdc.shop.model.transaction.entity;

import com.jdc.shop.model.master.entity.ProductStockHistory;
import com.jdc.shop.model.master.entity.ProductStockHistoryPk;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class CancelProduct extends ProductStockHistory {

	public CancelProduct() {
		var pk = new ProductStockHistoryPk();
		pk.setAction(Action.Cancel);
		setId(pk);
	}
	
	@ManyToOne
	private Cancel cancel;
}
