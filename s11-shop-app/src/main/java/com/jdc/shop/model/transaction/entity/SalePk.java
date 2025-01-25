package com.jdc.shop.model.transaction.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.jdc.shop.model.master.entity.ProductStockAction;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SalePk implements Serializable , ProductStockAction{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "issue_at")
	private LocalDate issueAt;
	
	@Column(name = "seq_number")
	private int seqNumber;

}
