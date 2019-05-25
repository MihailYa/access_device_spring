package com.mihailya.coursework.accessDevice.data;

import com.mihailya.coursework.accessDevice.data.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class Database {
	private AccessCardDao accessCardDao;
	private PersonDao personDao;
	private ScheduleDao scheduleDao;
	private VisitorsJournalDao visitorsJournalDao;
	private LockedCardsJournalDao lockedCardsJournalDao;

	private AdminDao adminDao;

	private Logger log = LoggerFactory.getLogger(Database.class.getName());

	public Database(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		accessCardDao = new AccessCardDao(jdbcTemplate);
		personDao = new PersonDao(jdbcTemplate);
		scheduleDao = new ScheduleDao(jdbcTemplate);
		visitorsJournalDao = new VisitorsJournalDao(jdbcTemplate);
		lockedCardsJournalDao = new LockedCardsJournalDao(jdbcTemplate);

		adminDao = new AdminDao(jdbcTemplate);
	}

	public void clearDatabase() {
		visitorsJournalDao.deleteAll();
		lockedCardsJournalDao.deleteAll();
		accessCardDao.deleteAll();
		personDao.deleteAll();
		scheduleDao.deleteAll();

		adminDao.deleteAll();
	}


	public AccessCardDao getAccessCardDao() {
		return accessCardDao;
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	public ScheduleDao getScheduleDao() {
		return scheduleDao;
	}

	public VisitorsJournalDao getVisitorsJournalDao() {
		return visitorsJournalDao;
	}

	public LockedCardsJournalDao getLockedCardsJournalDao() {
		return lockedCardsJournalDao;
	}

	public AdminDao getAdminDao() {
		return adminDao;
	}
}
