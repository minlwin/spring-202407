package com.jdc.shop.model.master.entity;

import com.jdc.shop.model.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class ProductStock extends AbstractEntity {

	@Id
	private int id;
	
	@MapsId
	@OneToOne(optional = false)
	private Product product;
	
	private int stock;
	
}
