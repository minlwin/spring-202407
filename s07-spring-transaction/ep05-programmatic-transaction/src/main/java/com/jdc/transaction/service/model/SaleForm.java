package com.jdc.transaction.service.model;

import java.util.List;

public record SaleForm(
		int memberId, List<SaleItem> items) {

}
