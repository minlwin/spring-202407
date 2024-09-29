package com.jdc.transaction.repo;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.jdc.transaction.service.model.SaleResult.Status;

@Repository
public class SaleHistoryRepoImpl implements SaleHistoryRepo {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	@Override
	public int create(int memberId) {
		
		var keyHolder = new GeneratedKeyHolder();
		
		template.update("insert into SALE_HISTORY (members_id) values (:member)", 
				new MapSqlParameterSource("member", memberId), 
				keyHolder, new String[] {"id"});
		return keyHolder.getKey().intValue();
	}

	@Override
	public void update(int id, Status status, String remark) {

		template.update("update SALE_HISTORY set status = :status, remark = :remark, update_at = :updateAt where id = :id", 
				new MapSqlParameterSource()
					.addValue("status", status.name())
					.addValue("remark", remark)
					.addValue("updateAt", Timestamp.valueOf(LocalDateTime.now()))
					.addValue("id", id)
				);
	}

}
