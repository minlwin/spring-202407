package com.jdc.shop.controller.output;

import java.util.Optional;

import com.jdc.shop.model.transaction.entity.SaleProduct;

public record SaleProductInfo(
		int productId, 
		String productName, 
		String productCategory, 
		String productImage,
		int sellPrice, 
		int quantity) {
	
	public int getAmount() {
		return sellPrice * quantity;
	}
	
	public String getCoverPhoto() {
		return Optional.ofNullable(productImage)
				.map(a -> a.split(","))
				.filter(a -> a.length > 0)
				.map(a -> a[0])
				.map(a -> "images/%s".formatted(a))
				.orElse("photo/default.jpg");
	}

	public static SaleProductInfo from(SaleProduct entity) {
		return new Builder()
				.productId(entity.getProduct().getId())
				.productName(entity.getProduct().getName())
				.productCategory(entity.getProduct().getCategory().getName())
				.productImage(entity.getProduct().getImage())
				.sellPrice(entity.getSalePrice())
				.quantity(entity.getQuantity())
				.build();
	}


	public static class Builder {
		private int productId;
		private String productName;
		private String productCategory;
		private String productImage;
		private int sellPrice;
		private int quantity;

		public Builder productId(int productId) {
			this.productId = productId;
			return this;
		}

		public Builder productName(String productName) {
			this.productName = productName;
			return this;
		}

		public Builder productCategory(String productCategory) {
			this.productCategory = productCategory;
			return this;
		}

		public Builder productImage(String productImage) {
			this.productImage = productImage;
			return this;
		}

		public Builder sellPrice(int sellPrice) {
			this.sellPrice = sellPrice;
			return this;
		}

		public Builder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public SaleProductInfo build() {
			return new SaleProductInfo(productId, productName, productCategory, productImage, sellPrice, quantity);
		}
	}

	public int getTotal() {
		return sellPrice * quantity;
	}
}
