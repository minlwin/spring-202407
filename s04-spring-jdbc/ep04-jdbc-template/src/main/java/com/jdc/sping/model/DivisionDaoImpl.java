package com.jdc.sping.model;

import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.jdc.sping.model.dto.Division;
import com.jdc.sping.model.dto.DivisionForm;

@Repository
public class DivisionDaoImpl implements DivisionDao {

	private JdbcTemplate template;
	private RowMapper<Division> rowMapper;

	public DivisionDaoImpl(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
		rowMapper = new DataClassRowMapper<>(Division.class);
	}

	@Override
	public List<Division> findByName(String name) {
		var sql = "select * from division where name = ?";
		return template.query(sql, rowMapper, name);
	}

	@Override
	public List<Division> findByRegion(String region) {
		var sql = "select * from division where region like ?";
		return template.query(sql, rowMapper, "%%%s%%".formatted(region));
	}

	@Override
	public List<Division> findByCapital(String capital) {
		var sql = "select * from division where capital = ?";
		return template.query(sql, rowMapper, capital);
	}

	@Override
	public long countAll() {
		var sql = "select count(id) from division";
		return template.queryForObject(sql, Long.class);
	}

	@Override
	public Division findById(int id) {
		var sql = "select * from division where id = ?";
		return template.queryForStream(sql, rowMapper, id)
				.findAny()
				.orElse(null);
	}

	@Override
	public int create(DivisionForm form) {
		var sql = "insert into division (name, capital, region) values (?, ?, ?)";
		var keys = new GeneratedKeyHolder();

		template.update(con -> {
			var stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, form.name());
			stmt.setString(2, form.capital());
			stmt.setString(3, form.region());
			return stmt;
		}, keys);

		return keys.getKey().intValue();
	}

	@Override
	public boolean update(int id, DivisionForm form) {

		var sql = "update division set name = ?, capital = ?, region = ? where id = ?";

		return template.update(sql, form.name(), form.capital(), form.region(), id) == 1;
	}

	@Override
	public boolean delete(int id) {
		var sql = "delete division where id = ?";
		return template.update(sql, id) == 1;
	}

}
