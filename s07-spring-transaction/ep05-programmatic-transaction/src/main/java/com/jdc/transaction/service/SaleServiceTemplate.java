package com.jdc.transaction.service;

import java.util.LinkedHashMap;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import com.jdc.transaction.repo.MemberRepo;
import com.jdc.transaction.repo.ProductRepo;
import com.jdc.transaction.repo.SaleHistoryRepo;
import com.jdc.transaction.repo.SaleItemRepo;
import com.jdc.transaction.service.model.ProductInfo;
import com.jdc.transaction.service.model.SaleForm;
import com.jdc.transaction.service.model.SaleResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Profile("template")
public class SaleServiceTemplate extends AbstractSaleService {

	private final MemberRepo memberRepo;
	private final ProductRepo productRepo;
	private final SaleHistoryRepo saleHistoryRepo;
	private final SaleItemRepo saleItemRepo;
	
	private final TransactionTemplate template;

	@Override
	public SaleResult checkOut(SaleForm form) {
		
		validate(form);

		var id = template.execute(status -> saleHistoryRepo.create(form.memberId()));

		var params = new LinkedHashMap<ProductInfo, Integer>();

		for (var item : form.items()) {
			var product = productRepo.findById(item.productId());

			if (product.isEmpty() || item.quantity() == 0) {
				return template.execute(status -> {
					var result = new SaleResult(id, SaleResult.Status.Error,
							product.isEmpty() ? "Invalid product id." : "Please enter quentity.");
					saleHistoryRepo.update(id, result.status(), result.message());
					return result;
				});
			}

			// Add Item to Parameter
			var productInfo = product.get();
			var quantity = params.get(productInfo) == null ? item.quantity()
					: params.get(productInfo) + item.quantity();

			params.put(productInfo, quantity);

		}

		return template.execute(status -> {
			// Create Sale Items
			saleItemRepo.save(id, params);

			// Update Sale History
			var result = new SaleResult(id, SaleResult.Status.Success, "Check out operation is done successfully.");
			saleHistoryRepo.update(id, result.status(), result.message());
			return result;
		});
	}

	@Override
	public MemberRepo memberRepo() {
		return memberRepo;
	}

}
