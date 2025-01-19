package com.jdc.shop.model.transaction.entity;

import com.jdc.shop.model.master.entity.ProductStockHistory;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class SaleProduct extends ProductStockHistory {

	@ManyToOne(optional = false)
	private Sale sale;
	
	private int salePrice;
}
