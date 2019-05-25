package com.mihailya.coursework.accessDevice.data.entities;

public class AccessCard extends BaseEntity {
	private int personId;
	private int scheduleId;
	private boolean isLocked;

	private Person person;
	private Schedule schedule;

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean locked) {
		isLocked = locked;
	}

	@Override
	public String toString() {
		return "AccessCard{" +
		       "id=" + id +
		       ", personId=" + personId +
		       ", scheduleId=" + scheduleId +
		       ", isLocked=" + isLocked +
		       ", person=" + person +
		       ", schedule=" + schedule +
		       '}';
	}
}
