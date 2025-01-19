package com.jdc.shop.model.transaction.entity;

import java.util.List;

import com.jdc.shop.model.AbstractEntity;
import com.jdc.shop.model.account.entity.Supplier;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Purchase extends AbstractEntity {

	@EmbeddedId
	private PurchasePk id;
	
	@ManyToOne(optional = false)
	private Supplier supplier;
	
	@OneToMany(mappedBy = "purchase")
	private List<PurchaseProduct> products;
	
	private Status status;
	private String remark;
	
	public enum Status {
		Initiate, Success, Error
	}
}
