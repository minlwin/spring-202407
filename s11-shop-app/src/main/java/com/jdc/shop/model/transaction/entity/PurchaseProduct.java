package com.jdc.shop.model.transaction.entity;

import com.jdc.shop.model.master.entity.ProductStockHistory;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class PurchaseProduct extends ProductStockHistory {

	@ManyToOne
	private Purchase purchase;
	
	private int buyPrice;
	private int sellPrice;
}
