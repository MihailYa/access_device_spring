package com.mihailya.coursework.accessDevice.data.dao;

import com.mihailya.coursework.accessDevice.data.Database;
import com.mihailya.coursework.accessDevice.data.entities.Person;
import com.mihailya.coursework.server.config.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDaoTest {
	private PersonDao personDao;

	@BeforeEach
	void refreshAccessCardDao() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Database database = new Database(context.getBean(DataSource.class));

		database.clearDatabase();
		personDao = database.getPersonDao();
	}

	@Test
	void getAll() {
		List<Person> lst = personDao.getAll();
		assertEquals(0, lst.size());
	}

	@Test
	void insert() {
		Person person = new Person();
		person.setName("testName1");
		person.setSurname("testSurname1");
		personDao.insert(person);

		List<Person> lst = personDao.getAll();

		assertEquals(1, lst.size());
		Person actualPerson = lst.get(0);
		assertEquals(person.getName(), actualPerson.getName());
		assertEquals(person.getSurname(), actualPerson.getSurname());
	}

	@Test
	void delete() {
		Person person = new Person();
		person.setName("testName1");
		person.setSurname("testSurname1");
		person.setId(personDao.insert(person));

		List<Person> lst1 = personDao.getAll();
		assertEquals(1, lst1.size());

		personDao.delete(person);

		List<Person> lst2 = personDao.getAll();
		assertEquals(0, lst2.size());
	}

	@Test
	void getById() {
		Person person1 = new Person();
		person1.setName("testName1");
		person1.setSurname("testSurname1");
		person1.setId(personDao.insert(person1));

		Person person2 = new Person();
		person2.setName("testName2");
		person2.setSurname("testSurname2");
		person2.setId(personDao.insert(person2));

		assertEquals(2, personDao.getAll().size());

		Person actualPerson = personDao.getById(person1.getId());

		assertEquals(person1.getName(), actualPerson.getName());
		assertEquals(person1.getSurname(), actualPerson.getSurname());
	}

	@Test
	void getByIdEmpty() {
		assertEquals(0, personDao.getAll().size());
		assertThrows(EmptyResultDataAccessException.class, () -> personDao.getById(1));
	}

	@Test
	void update() {
		Person person = new Person();
		person.setName("testName1");
		person.setSurname("testSurname1");
		person.setId(personDao.insert(person));

		assertEquals(1, personDao.getAll().size());

		person.setName("AnotherTestName1");
		person.setSurname("AnotherTestSurname1");
		personDao.update(person);

		List<Person> list = personDao.getAll();

		assertEquals(1, list.size());

		Person actualPerson = list.get(0);

		assertEquals(person.getName(), actualPerson.getName());
		assertEquals(person.getSurname(), actualPerson.getSurname());
	}
}