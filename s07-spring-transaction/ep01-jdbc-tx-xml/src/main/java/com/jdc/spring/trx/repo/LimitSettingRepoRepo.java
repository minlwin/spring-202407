package com.jdc.spring.trx.repo;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.jdc.spring.trx.dto.output.LimitSettingDto;
import com.jdc.spring.trx.utils.constants.TransactionType;
import com.jdc.spring.trx.utils.constants.UserLevel;

@Repository
public class LimitSettingRepoRepo implements LimitSettingRepo{
	
	private final JdbcClient jdbcClient;
	private final RowMapper<LimitSettingDto> rowMapper;
	
	public LimitSettingRepoRepo(DataSource dataSource) {
		this.jdbcClient = JdbcClient.create(dataSource);
		this.rowMapper = new DataClassRowMapper<>(LimitSettingDto.class);
	}

	@Override
	public Optional<LimitSettingDto> findById(UserLevel level, TransactionType type) {
		return jdbcClient.sql("select * from LIMIT_SETTING where user_level = ? and trx_type = ?")
				.param(level)
				.param(type)
				.query(rowMapper)
				.optional();
	}

}
