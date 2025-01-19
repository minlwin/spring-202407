package com.jdc.shop.controller.input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import lombok.Data;

@Data
public class PurchaseForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String supplierCode;
	
	private String supplierName;
	private String phone;
	private String email;
	private String shopName;
	private String address;
	private SupplierFormMode mode = SupplierFormMode.Search;
	
	public enum SupplierFormMode {
		Search, Create
	}
	
	private List<PurchaseFormItem> items = new ArrayList<>();
	
	private List<String> errors = new ArrayList<>();
	
	public int getTotal() {
		return items.stream().mapToInt(a -> a.getTotal()).sum();
	}
	
	public void addItem() {
		items.add(new PurchaseFormItem());
	}
	
	public void removeItem(int index) {
		items.remove(index);
		
		if(items.isEmpty()) {
			addItem();
		}
	}
	
	public void validateSupplier() {
		errors.clear();
		
		if(mode == SupplierFormMode.Search) {
			if(!StringUtils.hasLength(supplierCode)) {
				errors.add("Please enter supplier code.");
			}
		} else {
			if(!StringUtils.hasLength(supplierName)) {
				errors.add("Please enter supplier name.");
			}
			
			if(!StringUtils.hasLength(shopName)) {
				errors.add("Please enter shop name.");
			}

			if(!StringUtils.hasLength(phone)) {
				errors.add("Please enter phone number.");
			}
		}
	}
}
