package com.jdc.shop.model.transaction.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SaleSeq {

	@Id
	private LocalDate issueAt;
	private int seqNumber;

	public SalePk next() {
		return new SalePk(issueAt, ++ seqNumber);
	}
}
