package com.jdc.portal.api.model.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Contact {

	private String phone;
	private String address;
	private String township;
	private String region;
}
