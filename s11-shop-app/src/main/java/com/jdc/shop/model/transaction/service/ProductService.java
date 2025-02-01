package com.jdc.shop.model.transaction.service;

import static com.jdc.shop.utils.EntityOperationUtils.safeCall;

import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.input.ProductSearch;
import com.jdc.shop.controller.input.PurchaseFormItem;
import com.jdc.shop.controller.output.ProductDetails;
import com.jdc.shop.controller.output.ProductInfo;
import com.jdc.shop.model.PageInfo;
import com.jdc.shop.model.master.entity.Category;
import com.jdc.shop.model.master.entity.Product;
import com.jdc.shop.model.master.entity.ProductStock;
import com.jdc.shop.model.master.entity.Product_;
import com.jdc.shop.model.master.repo.ProductRepo;
import com.jdc.shop.model.master.repo.ProductStockRepo;
import com.jdc.shop.model.transaction.entity.PurchaseProduct;
import com.jdc.shop.model.transaction.entity.PurchaseProduct_;
import com.jdc.shop.model.transaction.entity.Purchase_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

	public PageInfo<ProductInfo> findBySupplier(int supplierId, int page, int size) {
		return search(ProductSearch.withSupplier(supplierId), page, size);
	}

	public PageInfo<ProductInfo> search(ProductSearch form, int page, int size) {
		return productRepo.search(queryFunc(form), countFunc(form), page, size);
	}

	private Function<CriteriaBuilder, CriteriaQuery<ProductInfo>> queryFunc(ProductSearch form) {
		return cb -> {
			var cq = cb.createQuery(ProductInfo.class);
			var root = cq.from(Product.class);
			
			var stockHistory = root.join(Product_.stockHistory, JoinType.LEFT);
			var productPurchase = cb.treat(stockHistory, PurchaseProduct.class);
			var purchase = productPurchase.join(PurchaseProduct_.purchase);
			var supplier = purchase.join(Purchase_.supplier);
			
			ProductInfo.select(cq, root, supplier);
			
			cq.where(form.where(cb, root, supplier, stockHistory));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(ProductSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Product.class);
			
			var stockHistory = root.join(Product_.stockHistory, JoinType.LEFT);
			var productPurchase = cb.treat(stockHistory, PurchaseProduct.class);
			var purchase = productPurchase.join(PurchaseProduct_.purchase);
			var supplier = purchase.join(Purchase_.supplier);
			
			cq.select(cb.count(root));
			cq.where(form.where(cb, root, supplier, stockHistory));
			
			return cq;
		};
	}

	public ProductDetails findById(int id) {
		return safeCall(productRepo.findById(id)
				.map(ProductDetails::from), "Product", "id", id);
	}

}
