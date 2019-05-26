package com.mihailya.coursework.accessDevice.deviceComponents;

import com.mihailya.coursework.accessDevice.data.Database;
import com.mihailya.coursework.accessDevice.data.dao.*;
import com.mihailya.coursework.accessDevice.data.entities.*;

import java.util.List;

public class AdminPanel {
	private Database database;

	public AdminPanel(Database database) {
		this.database = database;
	}

	public void deleteAccessCard(AccessCard accessCard) {
		AccessCardDao accessCardDao = database.getAccessCardDao();
		accessCardDao.delete(accessCard);
	}

	public void updateAccessCard(AccessCard accessCard) {
		AccessCardDao accessCardDao = database.getAccessCardDao();
		PersonDao personDao = database.getPersonDao();
		ScheduleDao scheduleDao = database.getScheduleDao();

		accessCardDao.update(accessCard);
		personDao.update(accessCard.getPerson());
		scheduleDao.update(accessCard.getSchedule());
	}

	public void insertAccessCard(AccessCard accessCard) {
		AccessCardDao accessCardDao = database.getAccessCardDao();
		PersonDao personDao = database.getPersonDao();
		ScheduleDao scheduleDao = database.getScheduleDao();

		int personId = personDao.insert(accessCard.getPerson());
		int scheduleId = scheduleDao.insert(accessCard.getSchedule());

		accessCard.setPersonId(personId);
		accessCard.setScheduleId(scheduleId);
		accessCardDao.insert(accessCard);
	}

	public List<AccessCard> getAllAccessCards() {
		AccessCardDao accessCardDao = database.getAccessCardDao();
		PersonDao personDao = database.getPersonDao();
		ScheduleDao scheduleDao = database.getScheduleDao();

		List<AccessCard> accessCards = accessCardDao.getAll();

		for (AccessCard accessCard : accessCards) {
			Person person = personDao.getById(accessCard.getPersonId());
			accessCard.setPerson(person);

			Schedule schedule = scheduleDao.getById(accessCard.getScheduleId());
			accessCard.setSchedule(schedule);
		}

		return accessCards;
	}

	public List<VisitRecord> getAllVisitRecords() {
		VisitorsJournalDao visitorsJournalDao = database.getVisitorsJournalDao();

		return visitorsJournalDao.getAll();
	}

	public List<LockCardRecord> getAllLockCardRecords() {
		LockedCardsJournalDao lockedCardsJournalDao = database.getLockedCardsJournalDao();

		return lockedCardsJournalDao.getAll();
	}
}
