package com.jdc.shop.model.transaction.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PurchasePk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private LocalDate issueAt;
	private int seqNumber;
}
