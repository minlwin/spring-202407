package com.jdc.shop.model.transaction.entity;

import java.time.LocalDateTime;

import com.jdc.shop.model.AbstractEntity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Cancel extends AbstractEntity{

	@EmbeddedId
	private CancelPk id;

	private LocalDateTime cancledAt;
	private String canceldBy;
}
