package com.mihailya.coursework.accessDevice.deviceComponents;

import com.mihailya.coursework.accessDevice.data.Database;
import com.mihailya.coursework.accessDevice.data.dao.AccessCardDao;
import com.mihailya.coursework.accessDevice.data.dao.AdminDao;
import com.mihailya.coursework.accessDevice.data.entities.AccessCard;
import com.mihailya.coursework.accessDevice.data.entities.Admin;
import com.mihailya.coursework.accessDevice.data.entities.LockCardRecord;
import com.mihailya.coursework.accessDevice.data.entities.VisitRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Properties;

public class Memory {
	private static String PROPERTIES_FILE_NAME = "/memory.properties";

	private static String PROPERTY_NAME_ACCESS_CODE = "accessCode";
	private static String PROPERTY_NAME_CONTROL_CODE = "controlCode";

	private Logger log = LoggerFactory.getLogger(Memory.class.getName());
	private String accessCode;
	private String controlCode;
	private Properties properties;
	private Database database;

	private AdminPanel adminPanel;

	private TaskExecutor taskExecutor;

	public Memory(TaskExecutor taskExecutor, DataSource dataSource) {
		this.taskExecutor = taskExecutor;
		database = new Database(dataSource);
		properties = new Properties();

		try (InputStream inputStream = getClass().getResourceAsStream(PROPERTIES_FILE_NAME)) {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		accessCode = properties.getProperty(PROPERTY_NAME_ACCESS_CODE);
		controlCode = properties.getProperty(PROPERTY_NAME_CONTROL_CODE);
	}

	public void modifyAccessCode(String newAccessCode) {
		log.info("Access code modified: " + newAccessCode);
		accessCode = newAccessCode;
		setProperty(PROPERTY_NAME_ACCESS_CODE, accessCode);
	}

	public void modifyControlCode(String newControlCode) {
		log.info("Control code modified: " + newControlCode);
		controlCode = newControlCode;
		setProperty(PROPERTY_NAME_CONTROL_CODE, controlCode);
	}

	public boolean verifyAccessCode(String accessCode) {
		return this.accessCode.equals(accessCode);
	}

	public boolean verifyControlCode(String controlCode) {
		return this.controlCode.equals(controlCode);
	}

	public boolean verifyCard(AccessCard accessCard) {
		AccessCard actualAccessCard = database.getAccessCardDao().getById(accessCard.getId());
		if(actualAccessCard == null)
			return false;

		accessCard.setPersonId(actualAccessCard.getPersonId());
		accessCard.setScheduleId(actualAccessCard.getScheduleId());

		accessCard.setPerson(database.getPersonDao().getById(accessCard.getPersonId()));
		accessCard.setSchedule(database.getScheduleDao().getById(accessCard.getScheduleId()));
		accessCard.setLocked(actualAccessCard.isLocked());

		LocalTime curTime = LocalTime.now();

		return !accessCard.isLocked() && curTime.isAfter(accessCard.getSchedule().getBeginTime()) && curTime.isBefore(accessCard.getSchedule().getEndTime());
	}

	public void lockCard(AccessCard accessCard) {
		AccessCardDao accessCardDao = database.getAccessCardDao();
		AccessCard actualAccessCard = accessCardDao
		                                      .getById(accessCard.getId());
		actualAccessCard.setLocked(true);

		log.info("Lock access card: " + actualAccessCard);
		accessCardDao.update(actualAccessCard);

		LockCardRecord lockCardRecord = new LockCardRecord();
		lockCardRecord.setAccessCardId(actualAccessCard.getId());
		lockCardRecord.setLockingDateTime(LocalDateTime.now());
		log.info("Insert lock card record: " + lockCardRecord);

		database.getLockedCardsJournalDao().insert(lockCardRecord);
	}

	public void recordCardAccess(AccessCard accessCard) {
		VisitRecord visitRecord = new VisitRecord();
		visitRecord.setAccessCardId(accessCard.getId());
		visitRecord.setVisitDateTime(LocalDateTime.now());

		log.info("Card access recorded: " + visitRecord);
		database.getVisitorsJournalDao().insert(visitRecord);
	}

	public boolean verifyAdmin(Admin admin) {
		AdminDao adminDao = database.getAdminDao();
		Admin foundAdmin = adminDao.find(admin);
		if(foundAdmin != null) {
			admin.setId(foundAdmin.getId());
			return true;
		}

		return false;
	}

	public AdminPanel getAdminPanel() {
		if(adminPanel == null)
			adminPanel = new AdminPanel(database);

		return adminPanel;
	}

	private void setProperty(String propertyName, String value) {
		properties.setProperty(propertyName, value);
		try (FileOutputStream fileOutputStream =
				     new FileOutputStream(getClass().getResource(PROPERTIES_FILE_NAME).getFile().replace("%20", " "))) {
			properties.store(fileOutputStream, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
