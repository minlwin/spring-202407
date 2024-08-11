package com.jdc.spring.trx.repo;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.jdc.spring.trx.dto.input.CashInForm;
import com.jdc.spring.trx.dto.output.AccountDto;
import com.jdc.spring.trx.utils.constants.TransactionStatus;

@Repository
public class TransactionBaseRepoImpl implements TransactionBaseRepo {
	
	private final JdbcClient jdbcClient;
	
	public TransactionBaseRepoImpl(DataSource dataSource) {
		jdbcClient = JdbcClient.create(dataSource);
	}

	@Override
	public int create(CashInForm form, AccountDto account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateStatus(int trxId, TransactionStatus success) {
		// TODO Auto-generated method stub
		
	}

}
