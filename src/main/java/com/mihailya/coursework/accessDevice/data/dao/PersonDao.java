package com.mihailya.coursework.accessDevice.data.dao;

import com.mihailya.coursework.accessDevice.data.entities.Person;
import com.mihailya.coursework.accessDevice.data.util.SqlStatementsManager;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDao extends AbstractDao<Person> {

	public PersonDao(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}


	/*private static final String UPDATE_STATEMENT = "UPDATE PERSONS SET PERSON_NAME = ?, PERSON_SURNAME = ? WHERE "
	                                               + "PERSON_ID = ?";

	@Override
	public boolean update(Person entity) {

		PreparedStatement prepareStatement = getPrepareStatement(UPDATE_STATEMENT);
		try {
			prepareStatement.setString(1, entity.getName());
			prepareStatement.setString(2, entity.getSurname());
			prepareStatement.setInt(3, entity.getId());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closePrepareStatement(prepareStatement);
		}

		return true;
	}*/

	@Override
	protected String getStatement(SqlStatementsManager.OperationType operationType) {
		return SqlStatementsManager.getInstance()
		                           .getSqlStatement(Person.class,
		                                                           operationType);
	}

	@Override
	protected Person createEntityFromRow(ResultSet row) throws SQLException {
		Person entity = new Person();
		entity.setId(row.getInt(1));
		entity.setName(row.getString(2));
		entity.setSurname(row.getString(3));

		return entity;
	}

	@Override
	protected void fillStatement(PreparedStatement statement, Person entity) throws SQLException {
		statement.setString(1, entity.getName());
		statement.setString(2, entity.getSurname());
	}

	@Override
	protected void fillUpdateStatement(PreparedStatement statement, Person entity) throws SQLException {
		statement.setString(1, entity.getName());
		statement.setString(2, entity.getSurname());
		statement.setInt(3, entity.getId());
	}
}
