package com.mihailya.coursework.accessDevice.data.dao;

import com.mihailya.coursework.accessDevice.data.entities.Admin;
import com.mihailya.coursework.accessDevice.data.util.SqlStatementsManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDao extends AbstractDao<Admin> {

	public AdminDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	public Admin find(Admin entity) {
		String sql = getStatement(SqlStatementsManager.OperationType.FIND);
		List<Admin> lst = jdbcTemplate.query(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			fillStatement(preparedStatement, entity);
			return preparedStatement;
		}, createRowMapper());

		if (lst == null || lst.isEmpty()) {
			return null;
		}

		return lst.get(0);
	}

	@Override
	protected String getStatement(SqlStatementsManager.OperationType operationType) {
		return SqlStatementsManager.getInstance()
		                           .getSqlStatement(Admin.class,
		                                            operationType);
	}

	@Override
	protected Admin createEntityFromRow(ResultSet row) throws SQLException {
		Admin entity = new Admin();
		entity.setId(row.getInt(1));
		entity.setLogin(row.getString(2));
		entity.setPassword(row.getString(3));

		return entity;
	}

	@Override
	protected void fillStatement(PreparedStatement statement, Admin entity) throws SQLException {
		statement.setString(1, entity.getLogin());
		statement.setString(2, entity.getPassword());
	}

	@Override
	protected void fillUpdateStatement(PreparedStatement statement, Admin entity) throws SQLException {
		statement.setString(1, entity.getLogin());
		statement.setString(2, entity.getPassword());
		statement.setInt(3, entity.getId());
	}
}
