package com.mihailya.coursework.accessDevice.data.dao;

import com.mihailya.coursework.accessDevice.data.Database;
import com.mihailya.coursework.accessDevice.data.entities.Admin;
import com.mihailya.coursework.server.config.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminDaoTest {

	private AdminDao adminDao;

	@BeforeEach
	void refreshAccessCardDao() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		Database database = new Database(context.getBean(DataSource.class));

		database.clearDatabase();
		adminDao = database.getAdminDao();
	}

	@Test
	void find() {
		Admin admin = new Admin();
		admin.setLogin("testLogin1");
		admin.setPassword("testPassword1");
		admin.setId(adminDao.insert(admin));

		List<Admin> lst = adminDao.getAll();

		assertEquals(1, lst.size());

		Admin actual = lst.get(0);
		assertEquals(admin.getId(), actual.getId());
		assertEquals(admin.getLogin(), actual.getLogin());
		assertEquals(admin.getPassword(), actual.getPassword());

		admin.setId(-1);
		actual = adminDao.find(admin);
		assertEquals(admin.getLogin(), actual.getLogin());
		assertEquals(admin.getPassword(), actual.getPassword());
	}

	@Test
	void findEmpty() {
		assertEquals(0, adminDao.getAll().size());

		Admin admin = new Admin();
		admin.setLogin("testLogin1");
		admin.setPassword("testPassword1");

		assertNull(adminDao.find(admin));
	}
}