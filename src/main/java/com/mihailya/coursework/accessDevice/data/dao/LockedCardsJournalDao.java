package com.mihailya.coursework.accessDevice.data.dao;

import com.mihailya.coursework.accessDevice.data.entities.LockCardRecord;
import com.mihailya.coursework.accessDevice.data.util.Converter;
import com.mihailya.coursework.accessDevice.data.util.SqlStatementsManager;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LockedCardsJournalDao extends AbstractDao<LockCardRecord> {

	public LockedCardsJournalDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	protected String getStatement(SqlStatementsManager.OperationType operationType) {
		return SqlStatementsManager.getInstance()
		                           .getSqlStatement(LockCardRecord.class,
		                                            operationType);
	}

	@Override
	protected LockCardRecord createEntityFromRow(ResultSet row) throws SQLException {
		LockCardRecord entity = new LockCardRecord();
		entity.setId(row.getInt(1));
		entity.setAccessCardId(row.getInt(2));
		entity.setLockingDateTime(Converter.toLocalDateTime(row.getTimestamp(3)));

		return entity;
	}

	@Override
	protected void fillStatement(PreparedStatement statement, LockCardRecord entity) throws SQLException {
		statement.setInt(1, entity.getAccessCardId());
		statement.setTimestamp(2, Converter.toTimestamp(entity.getLockingDateTime()));
	}

	@Override
	protected void fillUpdateStatement(PreparedStatement statement, LockCardRecord entity) throws SQLException {
		statement.setInt(1, entity.getAccessCardId());
		statement.setTimestamp(2, Converter.toTimestamp(entity.getLockingDateTime()));
		statement.setInt(3, entity.getId());
	}


}
