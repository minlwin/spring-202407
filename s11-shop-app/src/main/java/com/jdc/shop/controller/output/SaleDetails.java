package com.jdc.shop.controller.output;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.jdc.shop.model.transaction.entity.Sale.Status;
import com.jdc.shop.model.transaction.entity.SalePk;

public record SaleDetails(
		SalePk id,
		UUID customerId,
		String customerName,
		String customerPhone,
		String customerEmail,
		LocalDateTime saleAt,
		Status status,
		List<SaleProductInfo> items) {

}
