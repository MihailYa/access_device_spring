package com.mihailya.coursework.server.commands.util;

import com.mihailya.coursework.accessDevice.data.entities.AccessCard;
import com.mihailya.coursework.accessDevice.data.entities.Person;
import com.mihailya.coursework.accessDevice.data.entities.Schedule;
import com.mihailya.coursework.server.controllers.MainController;

import java.time.LocalTime;
import java.util.Map;

public class EntityFiller {

	public static AccessCard fillAccessCard(Map<String, String> requestParams) {
		String accessCardId = requestParams.get(MainController.PARAM_ACCESS_CARD_ID);
		String personId = requestParams.get(MainController.PARAM_ACCESS_CARD_PERSON_ID);
		String scheduleId = requestParams.get(MainController.PARAM_ACCESS_CARD_SCHEDULE_ID);
		String isAccessCardLocked = requestParams.get(MainController.PARAM_IS_ACCESS_CARD_LOCKED);

		if (accessCardId == null
		    || personId == null
		    || scheduleId == null
		) {
			throw new RuntimeException("Wrong input data for access card");
		}

		AccessCard accessCard = new AccessCard();

		int accessCardIdInt = Integer.parseInt(accessCardId);
		int personIdInt = Integer.parseInt(personId);
		int scheduleIdInt = Integer.parseInt(scheduleId);

		accessCard.setId(accessCardIdInt);
		accessCard.setPersonId(personIdInt);
		accessCard.setScheduleId(scheduleIdInt);
		accessCard.setLocked(isAccessCardLocked != null);


		Person person = fillPersonData(requestParams);
		person.setId(personIdInt);
		accessCard.setPerson(person);

		Schedule schedule = fillScheduleData(requestParams);
		schedule.setId(scheduleIdInt);
		accessCard.setSchedule(schedule);

		return accessCard;
	}

	private static Person fillPersonData(Map<String, String> requestParams) {
		String accessCardPersonName = requestParams.get(MainController.PARAM_ACCESS_CARD_PERSON_NAME);
		String accessCardPersonSurname = requestParams.get(MainController.PARAM_ACCESS_CARD_PERSON_SURNAME);

		if (accessCardPersonName == null
		    || accessCardPersonSurname == null) {
			throw new RuntimeException("Wrong input data for person");
		}

		Person person = new Person();
		person.setName(accessCardPersonName);
		person.setSurname(accessCardPersonSurname);

		return person;
	}

	private static Schedule fillScheduleData(Map<String, String> requestParams) {
		String accessCardScheduleBeginTime = requestParams.get(MainController.PARAM_ACCESS_CARD_SCHEDULE_BEGIN_TIME);
		String accessCardScheduleEndTime = requestParams.get(MainController.PARAM_ACCESS_CARD_SCHEDULE_END_TIME);

		if (accessCardScheduleBeginTime == null
		    || accessCardScheduleEndTime == null) {
			throw new RuntimeException("Wrong input data for schedule");
		}

		Schedule schedule = new Schedule();
		schedule.setBeginTime(LocalTime.parse(accessCardScheduleBeginTime));
		schedule.setEndTime(LocalTime.parse(accessCardScheduleEndTime));

		return schedule;
	}

	public static AccessCard fillAccessCardData(Map<String, String> requestParams) {
		String isAccessCardLocked = requestParams.get(MainController.PARAM_IS_ACCESS_CARD_LOCKED);

		AccessCard accessCard = new AccessCard();
		accessCard.setLocked(isAccessCardLocked != null);


		Person person = fillPersonData(requestParams);
		accessCard.setPerson(person);

		Schedule schedule = fillScheduleData(requestParams);
		accessCard.setSchedule(schedule);

		return accessCard;
	}
}
