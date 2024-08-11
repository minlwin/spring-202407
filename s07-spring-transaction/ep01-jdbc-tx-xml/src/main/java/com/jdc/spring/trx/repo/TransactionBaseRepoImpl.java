package com.jdc.spring.trx.repo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.jdc.spring.trx.dto.input.TransactionBaseForm;
import com.jdc.spring.trx.utils.constants.TransactionStatus;
import com.jdc.spring.trx.utils.constants.TransactionType;

@Repository
public class TransactionBaseRepoImpl implements TransactionBaseRepo {
	
	private final JdbcClient jdbcClient;
	
	public TransactionBaseRepoImpl(DataSource dataSource) {
		jdbcClient = JdbcClient.create(dataSource);
	}

	@Override
	public int create(TransactionBaseForm form) {
		var keys = new GeneratedKeyHolder();
		
		jdbcClient.sql("""
				insert into TRX_BASE (trx_type, issue_at, status, ledger, account_id, 
				before_usb, amount, particular) values (?, ?, ?, ?, ?, ?, ?, ?)""")
			.param(form.type().name())
			.param(Timestamp.valueOf(LocalDateTime.now()))
			.param(TransactionStatus.Pending)
			.param(form.ledger())
			.param(form.account())
			.param(form.beforeAmount())
			.param(form.amount())
			.param(form.particular())
			.update(keys);
		
		return keys.getKey().intValue();
	}

	@Override
	public void updateStatus(int trxId, TransactionStatus status) {
		jdbcClient.sql("update TRX_BASE set status = ? where id = ?")
			.param(status.name())
			.param(trxId)
			.update();
	}

	@Override
	public Long findTotalAmount(String userId, TransactionType type, LocalDate now) {
		return jdbcClient.sql("""
					select sum(amount) from TRX_BASE where 
					account_id = ? and trx_type = ? and issue_at >= ? and issue_at < ?""")
				.param(userId)
				.param(type)
				.param(Timestamp.valueOf(now.atStartOfDay()))
				.param(Timestamp.valueOf(now.plusDays(1).atStartOfDay()))
				.query(Long.class)
				.single();
	}

}
