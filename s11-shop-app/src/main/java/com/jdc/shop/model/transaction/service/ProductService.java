package com.jdc.shop.model.transaction.service;

import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.input.ProductSearch;
import com.jdc.shop.controller.input.PurchaseFormItem;
import com.jdc.shop.controller.output.ProductInfo;
import com.jdc.shop.model.PageInfo;
import com.jdc.shop.model.master.entity.Category;
import com.jdc.shop.model.master.entity.Product;
import com.jdc.shop.model.master.entity.ProductStock;
import com.jdc.shop.model.master.entity.Product_;
import com.jdc.shop.model.master.repo.ProductRepo;
import com.jdc.shop.model.master.repo.ProductStockRepo;
import com.jdc.shop.model.transaction.entity.PurchaseProduct_;
import com.jdc.shop.model.transaction.entity.Purchase_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final CategoryService categoryService;
	private final ProductRepo productRepo;
	private final ProductStockRepo stockRepo;
	
	@Transactional
	public Product createOrGetProduct(PurchaseFormItem form) {
		
		var category = categoryService.getOrCreate(form.getCategory());
		
		return productRepo
				.findOneByCategoryIdAndName(category.getId(), form.getProductName())
				.orElseGet(() -> {
					return createProduct(category, form.getProductName());
				});
		
	}
	
	private Product createProduct(Category category, String name) {
		
		var product = new Product();
		product.setCategory(category);
		product.setName(name);
		
		product = productRepo.saveAndFlush(product);
		
		var stock = new ProductStock();
		stock.setProduct(product);
		product.setStock(stock);
		
		stockRepo.saveAndFlush(stock);
		
		return product;
	}

	@Transactional(readOnly = true)
	public PageInfo<ProductInfo> search(ProductSearch form, int page, int size) {
		return productRepo.search(queryFunc(form), countFunc(form), page, size);
	}

	private Function<CriteriaBuilder, CriteriaQuery<ProductInfo>> queryFunc(ProductSearch form) {
		return cb -> {
			var cq = cb.createQuery(ProductInfo.class);
			var root = cq.from(Product.class);
			
			var supplier = root.join(Product_.purchases, JoinType.LEFT)
					.join(PurchaseProduct_.purchase, JoinType.LEFT)
					.join(Purchase_.supplier, JoinType.LEFT);
			
			ProductInfo.select(cq, root, supplier);
			cq.where(form.where(cb, root, supplier));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(ProductSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Product.class);
			
			var supplier = root.join(Product_.purchases, JoinType.LEFT)
					.join(PurchaseProduct_.purchase, JoinType.LEFT)
					.join(Purchase_.supplier, JoinType.LEFT);
			
			cq.select(cb.count(root));
			cq.where(form.where(cb, root, supplier));
			
			return cq;
		};
	}

	public Object findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
