package com.jdc.spring.trx.model.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.jdc.spring.trx.model.dto.Account;

@Repository
public class AccountRepoImpl implements AccountRepo {
	
	@Autowired
	private JdbcClient client;
	private RowMapper<Account> rowMapper = new DataClassRowMapper<>(Account.class);

	@Override
	public List<Account> findAll() {
		return client.sql("select * from ACCOUNT")
				.query(rowMapper)
				.list();
	}

	@Override
	public Optional<Account> findById(int id) {
		return client.sql("select * from ACCOUNT where id = :id")
				.param("id", id)
				.query(rowMapper)
				.optional();
	}

	@Override
	public int update(int id, int amount) {
		return client.sql("update account set amount = :amount where id = :id")
				.param("amount", amount)
				.param("id", id)
				.update();
	}

}
