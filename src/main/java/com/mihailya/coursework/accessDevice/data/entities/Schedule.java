package com.mihailya.coursework.accessDevice.data.entities;

import java.time.LocalTime;

public class Schedule extends BaseEntity {
	private LocalTime beginTime;
	private LocalTime endTime;

	public LocalTime getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(LocalTime beginTime) {
		this.beginTime = beginTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Schedule{" +
		       "id=" + id +
		       ", beginTime=" + beginTime +
		       ", endTime=" + endTime +
		       '}';
	}
}
