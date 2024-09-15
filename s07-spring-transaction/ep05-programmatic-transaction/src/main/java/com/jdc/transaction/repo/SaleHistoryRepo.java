package com.jdc.transaction.repo;

import com.jdc.transaction.service.model.SaleResult.Status;

public interface SaleHistoryRepo {

	int create(int memberId);

	void update(int id, Status error, String string);

}
