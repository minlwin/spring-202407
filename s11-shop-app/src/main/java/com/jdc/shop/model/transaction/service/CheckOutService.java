package com.jdc.shop.model.transaction.service;

import static com.jdc.shop.utils.EntityOperationUtils.safeCall;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.shop.controller.input.ShippingAddressForm;
import com.jdc.shop.controller.input.ShoppingCart;
import com.jdc.shop.model.BusinessException;
import com.jdc.shop.model.account.entity.Address;
import com.jdc.shop.model.account.entity.Customer;
import com.jdc.shop.model.account.repo.AddressRepo;
import com.jdc.shop.model.account.repo.CustomerRepo;
import com.jdc.shop.model.master.repo.ProductRepo;
import com.jdc.shop.model.transaction.SaleIdGenerator;
import com.jdc.shop.model.transaction.entity.Sale;
import com.jdc.shop.model.transaction.entity.Sale.Status;
import com.jdc.shop.model.transaction.entity.SalePk;
import com.jdc.shop.model.transaction.entity.SaleProduct;
import com.jdc.shop.model.transaction.repo.SaleProductRepo;
import com.jdc.shop.model.transaction.repo.SaleRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckOutService {
	
	private final CustomerRepo customerRepo;
	private final AddressRepo addressRepo;
	private final SaleIdGenerator idGenerator;
	private final SaleRepo saleRepo;
	private final SaleProductRepo saleProductRepo;
	private final ProductRepo productRepo;
	
	@Transactional(rollbackFor = BusinessException.class)
	public SalePk checkOut(ShoppingCart shoppingCart) {
		
		// Get Login User
		var customer = getCustomer();
		
		// Save Address
		var address = saveAddress(customer, shoppingCart.getAddressForm());
		
		// Save Sale Information
		var sale = saveSale(customer, address);
		
		// Save All Sale Product
		for(var item : shoppingCart.getItems()) {
			var product = safeCall(productRepo.findById(item.getId()), "Product", "id", item.getId());
			
			var saleProduct = new SaleProduct();
			saleProduct.getId().setStockAction(sale.getId());
			saleProduct.setProduct(product);
			
			saleProduct.setSale(sale);
			saleProduct.setProduct(product);
			saleProduct.setSalePrice(product.getSalePrice());
			
			saleProduct.setBeforeStock(product.getStock().getStock());
			saleProduct.setQuantity(item.getQuantity());
			
			var stock = product.getStock();
			stock.setStock(saleProduct.getBeforeStock() - saleProduct.getQuantity());
			
			if(stock.getStock() < 0) {
				throw new BusinessException("%s is out of stock.".formatted(product.getName()));
			}
			
			saleProductRepo.save(saleProduct);
		}
		
		return sale.getId();
	}


	private Sale saveSale(Customer customer, Address address) {
		
		var sale = new Sale();
		sale.setId(idGenerator.next(LocalDate.now()));
		sale.setCustomer(customer);
		sale.setAddress(address);
		sale.setSaleAt(LocalDateTime.now());
		sale.setStatus(Status.Invoiced);
		
		return saleRepo.save(sale);
	}

	private Address saveAddress(Customer customer, ShippingAddressForm form) {
		
		
		var address = Optional.ofNullable(form.getId())
				.filter(id -> StringUtils.hasLength(id))
				.map(id -> UUID.fromString(id))
				.flatMap(addressRepo::findById)
				.orElseGet(() -> {
					var entity = new Address();
					entity.setCustomer(customer);
					return entity;
				});
		
		address.setBuilding(form.getBuilding());
		address.setStreet(form.getStreet());
		address.setQuarter(form.getQuarter());
		address.setTownship(form.getTownship());
		address.setRegion(form.getRegion());
		
		return addressRepo.save(address);
	}

	private Customer getCustomer() {
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		return safeCall(customerRepo.findOneByAccountEmail(username), "Customer", "login id", username);
	}

}
