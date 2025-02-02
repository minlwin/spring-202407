package com.jdc.shop.controller.output;

import java.time.LocalDateTime;
import java.util.Map;

import com.jdc.shop.model.master.entity.Product;

public record ProductDetails(
		int id,
		String category,
		String name,
		int salePrice,
		int stock,
		String image,
		String description,
		Map<String, String> properties,
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {
	
	public static ProductDetails from(Product entity) {
        return new Builder()
                .id(entity.getId())
                .category(entity.getCategory().getName())
                .name(entity.getName())
                .salePrice(entity.getSalePrice())
                .stock(entity.getStock().getStock())
                .image(entity.getImage())
                .description(entity.getDescription())
                .properties(entity.getProperties())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();	
    }

    public static class Builder {
        private int id;
        private String category;
        private String name;
        private int salePrice;
        private int stock;
        private String image;
        private String description;
        private Map<String, String> properties;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder salePrice(int salePrice) {
            this.salePrice = salePrice;
            return this;
        }

        public Builder stock(int stock) {
            this.stock = stock;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder properties(Map<String, String> properties) {
            this.properties = properties;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ProductDetails build() {
            return new ProductDetails(id, category, name, salePrice, stock, image, description, properties, createdAt, updatedAt);
        }
    }
 }
