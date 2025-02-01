package com.jdc.shop.controller.output;

import com.jdc.shop.model.account.entity.Supplier;
import com.jdc.shop.model.account.entity.Supplier_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record SupplierInfo(
	    int id,
	    String name,
	    String phone,
	    String email,
	    String shopName,
	    String address) {

	public static void select(CriteriaQuery<SupplierInfo> cq, Root<Supplier> root) {
		cq.multiselect(
			root.get(Supplier_.id),
			root.get(Supplier_.name),
			root.get(Supplier_.phone),
			root.get(Supplier_.email),
			root.get(Supplier_.shopName),
			root.get(Supplier_.address)			
		);
	}

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
