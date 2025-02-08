package com.jdc.shop.controller.output;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.jdc.shop.model.transaction.entity.Sale;
import com.jdc.shop.model.transaction.entity.Sale.Status;
import com.jdc.shop.model.transaction.entity.SalePk;

public record SaleDetails(SalePk id, 
		UUID customerId, 
		String customerName, 
		String customerPhone, 
		String customerEmail,
		LocalDateTime saleAt, 
		Status status, 
		List<SaleProductInfo> items) {
	
	public static SaleDetails from(Sale entity) {
		return new Builder()
				.id(entity.getId())
				.customerId(entity.getCustomer().getId())
				.customerName(entity.getCustomer().getName())
				.customerPhone(entity.getCustomer().getPhone())
				.customerEmail(entity.getCustomer().getAccount().getEmail())
				.saleAt(entity.getSaleAt())
				.status(entity.getStatus())
				.items(entity.getProducts().stream().map(SaleProductInfo::from).toList())
				.build();
	}

	public static class Builder {
		private SalePk id;
		private UUID customerId;
		private String customerName;
		private String customerPhone;
		private String customerEmail;
		private LocalDateTime saleAt;
		private Status status;
		private List<SaleProductInfo> items;

		public Builder id(SalePk id) {
			this.id = id;
			return this;
		}

		public Builder customerId(UUID customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder customerName(String customerName) {
			this.customerName = customerName;
			return this;
		}

		public Builder customerPhone(String customerPhone) {
			this.customerPhone = customerPhone;
			return this;
		}

		public Builder customerEmail(String customerEmail) {
			this.customerEmail = customerEmail;
			return this;
		}

		public Builder saleAt(LocalDateTime saleAt) {
			this.saleAt = saleAt;
			return this;
		}

		public Builder status(Status status) {
			this.status = status;
			return this;
		}

		public Builder items(List<SaleProductInfo> items) {
			this.items = items;
			return this;
		}

		public SaleDetails build() {
			return new SaleDetails(id, customerId, customerName, customerPhone, customerEmail, saleAt, status, items);
		}
	}
}
