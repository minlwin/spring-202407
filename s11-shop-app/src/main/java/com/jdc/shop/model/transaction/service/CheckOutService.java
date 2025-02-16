package com.jdc.shop.model.transaction.service;

import static com.jdc.shop.utils.EntityOperationUtils.safeCall;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.controller.input.ShippingAddressForm;
import com.jdc.shop.controller.input.ShoppingCart;
import com.jdc.shop.controller.input.ShoppingCartItem;
import com.jdc.shop.model.account.entity.Address;
import com.jdc.shop.model.account.entity.Customer;
import com.jdc.shop.model.account.repo.CustomerRepo;
import com.jdc.shop.model.transaction.entity.Sale;
import com.jdc.shop.model.transaction.entity.SalePk;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckOutService {
	
	private final CustomerRepo customerRepo;
	
	@Transactional
	public SalePk checkOut(ShoppingCart shoppingCart) {
		
		// Get Login User
		var customer = getCustomer();
		
		// Save Address
		var address = saveAddress(shoppingCart.getAddressForm());
		
		// Save Sale Information
		var sale = saveSale(customer, address);
		
		// Save All Sale Product
		saveSaleProducts(sale, shoppingCart.getItems());
		
		return sale.getId();
	}

	private void saveSaleProducts(Sale sale, List<ShoppingCartItem> items) {
		// TODO Auto-generated method stub
		
	}

	private Sale saveSale(Customer customer, Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	private Address saveAddress(ShippingAddressForm addressForm) {
		// TODO Auto-generated method stub
		return null;
	}

	private Customer getCustomer() {
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		return safeCall(customerRepo.findOneByAccountEmail(username), "Customer", "login id", username);
	}

}
