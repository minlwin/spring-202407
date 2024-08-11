package com.jdc.spring.trx.repo;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.jdc.spring.trx.dto.input.BalanceHistoryForm;

@Repository
public class BalanceHistoryRepoRepo implements BalanceHistoryRepo {
	
	private final JdbcClient jdbcClient;
	
	public BalanceHistoryRepoRepo(DataSource dataSource) {
		jdbcClient = JdbcClient.create(dataSource);
	}

	@Override
	public void create(BalanceHistoryForm history) {
		jdbcClient.sql("insert into BALANCE_HISTORY values (?, ?, ?, ?, ?, ?)")
			.paramSource(new SimplePropertySqlParameterSource(history))
			.update();
	}

}
