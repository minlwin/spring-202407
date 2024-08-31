package com.jdc.spring.trx.repo;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jdc.spring.trx.dto.input.BalanceHistoryForm;
import com.jdc.spring.trx.dto.input.UserBalanceSearch;
import com.jdc.spring.trx.dto.output.UserBalanceInfo;

@Repository
public class BalanceHistoryRepoImpl implements BalanceHistoryRepo {
	
	private final JdbcClient jdbcClient;
	private final RowMapper<UserBalanceInfo> rowMapper;
	
	public BalanceHistoryRepoImpl(DataSource dataSource) {
		jdbcClient = JdbcClient.create(dataSource);
		rowMapper = new DataClassRowMapper<>(UserBalanceInfo.class);
	}

	@Override
	public void create(BalanceHistoryForm history) {
		jdbcClient.sql("insert into BALANCE_HISTORY values (?, ?, ?, ?, ?, ?)")
			.paramSource(new SimplePropertySqlParameterSource(history))
			.update();
	}

	@Override
	public List<UserBalanceInfo> search(UserBalanceSearch search) {
		
		var sql = new StringBuffer("""
				select a.level, b.account_id, a.name, b.trx_id, t.issue_at,
				t.trx_type, t.ledger, t.particular, b.before_amount, b.trx_amount 
				from BALANCE_HISTORY b join ACCOUNT a on b.account_id = a.login_id 
				join TRX_BASE t on b.trx_id = t.id where 1 = 1""");
		
		var params = new ArrayList<Object>();
		
		if(null != search.level()) {
			sql.append(" and a.level = ?");
			params.add(search.level().name());
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
				.param(params)
				.query(rowMapper)
				.list();
	}

}
