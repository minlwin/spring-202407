package com.jdc.shop.model.master.entity;

import java.time.LocalDate;

public interface ProductStockAction {

	LocalDate getIssueAt();
	int getSeqNumber();
}
