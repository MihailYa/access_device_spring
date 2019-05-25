package com.mihailya.coursework.accessDevice.data.dao;

import com.mihailya.coursework.accessDevice.data.entities.Schedule;
import com.mihailya.coursework.accessDevice.data.util.SqlStatementsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

public class ScheduleDao extends AbstractDao<Schedule> {

	private Logger log = LoggerFactory.getLogger(ScheduleDao.class.getName());

	public ScheduleDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	protected String getStatement(SqlStatementsManager.OperationType operationType) {
		return SqlStatementsManager.getInstance()
		                           .getSqlStatement(Schedule.class,
		                                            operationType);
	}

	@Override
	protected Schedule createEntityFromRow(ResultSet row) throws SQLException {
		Schedule entity = new Schedule();
		entity.setId(row.getInt(1));
		entity.setBeginTime(row.getTime(2)
		                       .toLocalTime());
		entity.setEndTime(row.getTime(3)
		                     .toLocalTime());

		return entity;
	}

	@Override
	protected void fillStatement(PreparedStatement statement, Schedule entity) throws SQLException {
		statement.setTime(1, Time.valueOf(entity.getBeginTime()));
		statement.setTime(2, Time.valueOf(entity.getEndTime()));
	}

	@Override
	protected void fillUpdateStatement(PreparedStatement statement, Schedule entity) throws SQLException {
		statement.setTime(1, Time.valueOf(entity.getBeginTime()));
		statement.setTime(2, Time.valueOf(entity.getEndTime()));
		statement.setInt(3, entity.getId());
	}


}
