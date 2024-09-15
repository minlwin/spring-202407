package com.jdc.transaction.repo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepoImpl implements MemberRepo {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Override
	public long countById(int memberId) {
		return template.queryForObject("select count(id) from MEMBERS where id = :id", 
				Map.of("id", memberId), Long.class);
	}

}
