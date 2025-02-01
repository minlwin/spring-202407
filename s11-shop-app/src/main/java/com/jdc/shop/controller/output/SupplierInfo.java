package com.jdc.shop.controller.output;

import com.jdc.shop.model.account.entity.Supplier;

public record SupplierInfo(
	    int id,
	    String name,
	    String phone,
	    String email,
	    String shopName,
	    String address) {

	public static SupplierInfo from(Supplier entity) {
        return new SupplierInfo(
                entity.getId(),
                entity.getName(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getShopName(),
                entity.getAddress()
            );	
    }
}
