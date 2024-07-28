package com.jdc.sping.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jdc.sping.model.dto.District;
import com.jdc.sping.model.dto.DistrictForm;

@Repository
public class DistrictDaoImpl implements DistrictDao {
	
	private SimpleJdbcInsert insert;
	private JdbcTemplate template;
	private RowMapper<District> rowMapper;

	public DistrictDaoImpl(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
		insert = new SimpleJdbcInsert(dataSource)
				.withTableName("district")
				.usingGeneratedKeyColumns("id");
		
		rowMapper = new DataClassRowMapper<>(District.class);
		
	}
	
	@Override
	public int create(DistrictForm form) {
		var keys = insert.executeAndReturnKey(new SimplePropertySqlParameterSource(form));
		return keys.intValue();
	}

	@Override
	public District findById(int id) {
		
		try(var stream = template.queryForStream("""
				select dis.id, dis.name, div.id divisionId, div.name divisionName, div.region 
				from district dis join division div on dis.division_id = div.id 
				where dis.id = ?""", rowMapper, id)) {
			
			return stream.findAny().orElse(null);
		}
	}

	@Override
	public long countAll() {
		return template.queryForObject("select count(id) from district", Long.class);
	}

	@Override
	public List<District> search(Integer divisionId, String region, String name) {
		
		var sb = new StringBuffer("""
				select dis.id, dis.name, div.id divisionId, div.name divisionName, div.region 
				from district dis join division div on dis.division_id = div.id where 1 = 1""");
		var params = new ArrayList<Object>();
		
		if(null != divisionId) {
			sb.append(" and dis.division_id = ?");
			params.add(divisionId);
		}
		
		if(StringUtils.hasLength(region)) {
			sb.append(" and div.region like ?");
			params.add("%%%s%%".formatted(region));
		}
		
		if(StringUtils.hasLength(name)) {
			sb.append(" and lower(dis.name) like ?");
			params.add("%s%%".formatted(name.toLowerCase()));
		}
		
		return template.query(sb.toString(), rowMapper, params.toArray());
	}

	@Override
	public boolean update(int id, DistrictForm form) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
