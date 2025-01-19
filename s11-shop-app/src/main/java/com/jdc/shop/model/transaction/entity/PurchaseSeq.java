package com.jdc.shop.model.transaction.entity;

import java.time.LocalDate;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseSeq {

	@Id
	private LocalDate issueAt;
	private int seqNumber;

	public PurchasePk next() {
		return new PurchasePk(issueAt, ++ seqNumber);
	}
}
