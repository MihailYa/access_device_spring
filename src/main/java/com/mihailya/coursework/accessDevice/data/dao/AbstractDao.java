package com.mihailya.coursework.accessDevice.data.dao;

import com.mihailya.coursework.accessDevice.data.entities.BaseEntity;
import com.mihailya.coursework.accessDevice.data.util.SqlStatementsManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<E extends BaseEntity> {
	protected JdbcTemplate jdbcTemplate;


	public AbstractDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public boolean delete(E entity) {
		String sql = getStatement(SqlStatementsManager.OperationType.DELETE);

		jdbcTemplate.update(sql, entity.getId());

		return true;
	}

	public void update(E entity) {
		String sql = getStatement(SqlStatementsManager.OperationType.UPDATE);

		jdbcTemplate.update(sql, preparedStatement -> fillUpdateStatement(preparedStatement, entity));

	}

	public int insert(E entity) {
		String query = getStatement(SqlStatementsManager.OperationType.INSERT);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query);
			fillStatement(ps, entity);
			return ps;
		}, keyHolder);

		Number key = keyHolder.getKey();
		if(key == null)
			return -1;

		return key.intValue();
	}

	public List<E> getAll() {
		String query = getStatement(SqlStatementsManager.OperationType.SELECT_ALL);

		return jdbcTemplate.query(query, createRowMapper());
	}

	public E getById(int id) {
		String query = getStatement(SqlStatementsManager.OperationType.SELECT_BY_ID);

		return jdbcTemplate.queryForObject(query, new Object[]{id}, createRowMapper());
	}

	public void deleteAll() {
		String sql = getStatement(SqlStatementsManager.OperationType.DELETE_ALL);
		jdbcTemplate.execute(sql);
	}

	protected abstract String getStatement(SqlStatementsManager.OperationType operationType);

	protected abstract E createEntityFromRow(ResultSet row) throws SQLException;

	protected abstract void fillStatement(PreparedStatement statement, E entity) throws SQLException;

	protected abstract void fillUpdateStatement(PreparedStatement statement, E entity) throws SQLException;

	protected RowMapper<E> createRowMapper() {
		return (resultSet, rowNum) -> createEntityFromRow(resultSet);
	}
}
