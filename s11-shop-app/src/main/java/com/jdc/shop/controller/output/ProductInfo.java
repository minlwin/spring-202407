package com.jdc.shop.controller.output;

import org.springframework.util.StringUtils;

import com.jdc.shop.model.account.entity.Supplier;
import com.jdc.shop.model.account.entity.Supplier_;
import com.jdc.shop.model.master.entity.Category_;
import com.jdc.shop.model.master.entity.Product;
import com.jdc.shop.model.master.entity.ProductStock_;
import com.jdc.shop.model.master.entity.Product_;
import com.jdc.shop.model.transaction.entity.Purchase;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

public record ProductInfo(
		int id,
		String category,
		String productName,
		String image,
		int sellPrice,
		int stock,
		String supplier,
		String shop,
		String phone) {
	
	public String getCoverPhoto() {
		if(StringUtils.hasLength(image)) {
			var array = image.split(",");
			if(array.length > 0) {
				return "images/%s".formatted(array[0]);
			}
		}
		
		return "photo/default.jpg";
	}

	public static void select(CriteriaQuery<ProductInfo> cq, Root<Product> root, Join<Purchase, Supplier> supplier) {
		
		cq.multiselect(
			root.get(Product_.id),
			root.get(Product_.category).get(Category_.name),
			root.get(Product_.name),
			root.get(Product_.image),
			root.get(Product_.salePrice),
			root.get(Product_.stock).get(ProductStock_.stock),
			supplier.get(Supplier_.name),
			supplier.get(Supplier_.shopName),
			supplier.get(Supplier_.phone)
		).distinct(true);
	}
}
