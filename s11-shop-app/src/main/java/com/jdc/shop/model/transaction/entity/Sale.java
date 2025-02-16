package com.jdc.shop.model.transaction.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.jdc.shop.model.AbstractEntity;
import com.jdc.shop.model.account.entity.Address;
import com.jdc.shop.model.account.entity.Customer;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Sale extends AbstractEntity {

	@EmbeddedId
	private SalePk id;
	
	@ManyToOne(optional = false)
	private Customer customer;
	
	@ManyToOne(optional = false)
	private Address address;
	
	private LocalDateTime saleAt;
	private Status status;
	private LocalDateTime satusChangeAt;
	private int delivery;
	private String remark;
	
	@OneToMany(mappedBy = "sale")
	private List<SaleProduct> products;
	
	
	public enum Status {
		Invoiced, Delivered, Cancel
	}
}
