package com.jdc.spring.trx.repo;

import javax.sql.DataSource;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.jdc.spring.trx.dto.output.TransferDetails;

@Repository
public class TransactionTransferRepoImpl implements TransactionTransferRepo{
	
	private final JdbcClient jdbcClient;
	private final RowMapper<TransferDetails> rowMapper;
	
	public TransactionTransferRepoImpl(DataSource dataSource) {
		this.jdbcClient = JdbcClient.create(dataSource);
		this.rowMapper = new DataClassRowMapper<>(TransferDetails.class);
	}

	@Override
	public TransferDetails findById(int id) {
		return jdbcClient.sql("""
				select b.id, b.trx_type, b.issue_at, b.status, b.ledger senderLedger, 
				s.login_id senderId, s.name senderName, s.level senderLevel, b.before_usb senderBefore, 
				r.login_id receiverId, r.name receiverName, r.level receiverLevel, t.before_tsfto receiverBefore, 
				b.amount, b.particular 
				from TRX_BASE b 
				join TRX_TRANSFER t on b.id = t.id 
				join ACCOUNT s on b.account_id = s.login_id 
				join ACCOUNT r on t.transfer_to = r.login_id 
				where b.id = ?""")
				.param(id)
				.query(rowMapper)
				.optional().orElse(null);
	}

	@Override
	public void create(int trxId, String loginId, int amount) {
		jdbcClient.sql("insert into TRX_TRANSFER values (?, ?, ?)")
			.param(trxId)
			.param(loginId)
			.param(amount)
			.update();
	}

}
