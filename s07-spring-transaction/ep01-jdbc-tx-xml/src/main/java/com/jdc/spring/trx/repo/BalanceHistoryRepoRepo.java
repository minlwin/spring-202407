package com.jdc.spring.trx.repo;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.jdc.spring.trx.dto.input.BalanceHistoryForm;
import com.jdc.spring.trx.dto.input.UserBalanceSearch;
import com.jdc.spring.trx.dto.output.UserBalanceInfo;

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

	@Override
	public List<UserBalanceInfo> search(UserBalanceSearch search) {
		// TODO Auto-generated method stub
		return null;
	}

}
