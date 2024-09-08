package com.jdc.spring.trx.model.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TrxHistoryRepoImpl implements TrxHistoryRepo {
	
	@Autowired
	private JdbcClient client;

	@Override
	public int create(String description, int amount, String status) {
		
		var keyHolder = new GeneratedKeyHolder();
		
		client.sql("insert into TRX_HISTORY (description, trx_amount, trx_status) values (?, ?, ?)")
				.param(description)
				.param(amount)
				.param(status)
				.update(keyHolder);
		
		return keyHolder.getKey().intValue();
	}

	@Override
	public void update(int id, String status) {
		client.sql("update TRX_HISTORY set trx_status = ? where id = ?")
			.param(status)
			.param(id)
			.update();
	}

	@Override
	public void update(int id, String status, String message) {
		client.sql("update TRX_HISTORY set trx_status = ?, error = ? where id = ?")
		.param(status)
		.param(message)
		.param(id)
		.update();
	}

}
