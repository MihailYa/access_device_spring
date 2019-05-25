package com.mihailya.coursework.accessDevice.data.dao;

import com.mihailya.coursework.accessDevice.data.entities.VisitRecord;
import com.mihailya.coursework.accessDevice.data.util.Converter;
import com.mihailya.coursework.accessDevice.data.util.SqlStatementsManager;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisitorsJournalDao extends AbstractDao<VisitRecord> {

	public VisitorsJournalDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	protected String getStatement(SqlStatementsManager.OperationType operationType) {
		return SqlStatementsManager.getInstance()
		                           .getSqlStatement(VisitRecord.class,
		                                            operationType);
	}

	@Override
	protected VisitRecord createEntityFromRow(ResultSet row) throws SQLException {
		VisitRecord entity = new VisitRecord();
		entity.setId(row.getInt(1));
		entity.setAccessCardId(row.getInt(2));
		entity.setVisitDateTime(Converter.toLocalDateTime(row.getTimestamp(3)));

		return entity;
	}

	@Override
	protected void fillStatement(PreparedStatement statement, VisitRecord entity) throws SQLException {
		statement.setInt(1, entity.getAccessCardId());
		statement.setTimestamp(2, Converter.toTimestamp(entity.getVisitDateTime()));
	}

	@Override
	protected void fillUpdateStatement(PreparedStatement statement, VisitRecord entity) throws SQLException {
		statement.setInt(1, entity.getAccessCardId());
		statement.setTimestamp(2, Converter.toTimestamp(entity.getVisitDateTime()));
		statement.setInt(3, entity.getId());
	}


}
