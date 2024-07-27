package com.jdc.spring.jdbc.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jdc.spring.jdbc.DivisionDao;
import com.jdc.spring.jdbc.model.Division;

@Repository
public class DivisionDaoPure implements DivisionDao {
	
	@Autowired
	private DataSource dataSource;
	
	private static final String SQL = "select * from division";

	@Override
	public List<Division> findAll() {
		
		var list = new ArrayList<Division>();
		
		try(var connection = dataSource.getConnection();
				var stmt = connection.prepareStatement(SQL)) {
			
			var resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				var division = new Division();
				division.setId(resultSet.getInt("id"));
				division.setName(resultSet.getString("name"));
				division.setCapital(resultSet.getString("capital"));
				division.setRegion(resultSet.getString("region"));
				list.add(division);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
