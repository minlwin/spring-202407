package com.jdc.spring.trx.repo;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.jdc.spring.trx.dto.output.AccountDto;

@Repository
public class AccountRepoImpl implements AccountRepo {
	
	private final JdbcClient jdbcClient;
	private final RowMapper<AccountDto> rowMapper;
	
	public AccountRepoImpl(DataSource dataSource) {
		jdbcClient = JdbcClient.create(dataSource);
		rowMapper = new DataClassRowMapper<>(AccountDto.class);
	}

	@Override
	public Optional<AccountDto> findById(String loginId) {
		return jdbcClient.sql("select * from ACCOUNT where login_id = ?")
				.param(loginId)
				.query(rowMapper)
				.optional();
	}

	@Override
	public void updateBalance(String loginId, int balance) {
		jdbcClient.sql("update ACCOUNT set amount = ? where login_id = ?")
			.param(balance)
			.param(loginId)
			.update();
	}
	
}
