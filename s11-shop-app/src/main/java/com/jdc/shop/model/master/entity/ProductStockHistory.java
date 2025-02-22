package com.jdc.shop.model.master.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProductStockHistory {

	@EmbeddedId
	private ProductStockHistoryPk id;
	
	private int beforeStock;
	private int quantity;
	private String remark;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Product product;
	
	public void setProduct(Product product) {
		this.product = product;
		this.id.setProductId(product.getId());
	}
	
	public enum Action {
		Buy, Sell, Cancel
	}
	
}
