package com.jdc.shop.model.master.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyyMMdd");
	
	@Column(name = "issue_at")
	private LocalDate issueAt;

	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "seq_number")
	private int seqNumber;
	
	private Action action;
	
	public void setStockAction(ProductStockAction stockAction) {
		this.issueAt = stockAction.getIssueAt();
		this.seqNumber = stockAction.getSeqNumber();
	}
	
	public String getCode() {
		// yyyyMMdd-001
		return "%s-%03d".formatted(issueAt.format(DF), seqNumber);
	}

}
