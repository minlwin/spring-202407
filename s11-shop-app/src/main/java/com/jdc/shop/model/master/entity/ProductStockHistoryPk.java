package com.jdc.shop.model.master.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.jdc.shop.model.master.entity.ProductStockHistory.Action;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ProductStockHistoryPk implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "issue_at")
	private LocalDate issueAt;
	@Column(name = "seq_number")
	private int seqNumber;
	@Column(name = "product_id")
	private int productId;
	private Action action;
}
