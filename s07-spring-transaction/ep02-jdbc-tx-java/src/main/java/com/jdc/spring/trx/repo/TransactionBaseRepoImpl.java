package com.jdc.spring.trx.repo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jdc.spring.trx.dto.input.TransactionBaseForm;
import com.jdc.spring.trx.dto.input.TransactionSearch;
import com.jdc.spring.trx.dto.output.TransactionInfo;
import com.jdc.spring.trx.utils.constants.TransactionStatus;
import com.jdc.spring.trx.utils.constants.TransactionType;

@Repository
public class TransactionBaseRepoImpl implements TransactionBaseRepo {
	
	private final JdbcClient jdbcClient;
	private final RowMapper<TransactionInfo> rowMapper;
	
	public TransactionBaseRepoImpl(DataSource dataSource) {
		jdbcClient = JdbcClient.create(dataSource);
		rowMapper = new DataClassRowMapper<>(TransactionInfo.class);
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

	@Override
	public List<TransactionInfo> search(TransactionSearch search) {
		
		var sql = new StringBuffer("""
				select t.id, t.trx_type, t.status, t.issue_at,
				a.level, t.account_id, a.name, t.amount, t.particular 
				from TRX_BASE t join ACCOUNT a on t.account_id = a.login_id 
				where 1 = 1""");
		
		var params = new ArrayList<Object>();
		
		if(null != search.trxType()) {
			sql.append(" and t.trx_type = ?");
			params.add(search.trxType().name());
		}
		
		if(null != search.status()) {
			sql.append(" and t.status = ?");
			params.add(search.status().name());
		}
		
		if(null != search.from()) {
			sql.append(" and t.issue_at >= ?");
			params.add(search.from().atStartOfDay());
		}
		
		if(null != search.to()) {
			sql.append(" and t.issue_at < ?");
			params.add(search.to().plusDays(1).atStartOfDay());
		}
		
		if(StringUtils.hasLength(search.keyword())) {
			sql.append(" and (lower(a.login_id) like ? or lower(a.name) like ?)");
			params.add(search.keyword().toLowerCase().concat("%"));
			params.add(search.keyword().toLowerCase().concat("%"));
		}
		
		return jdbcClient.sql(sql.toString())
				.params(params)
				.query(rowMapper)
				.list();
	}
}
