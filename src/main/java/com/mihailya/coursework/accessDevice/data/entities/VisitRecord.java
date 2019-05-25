package com.mihailya.coursework.accessDevice.data.entities;

import java.time.LocalDateTime;

public class VisitRecord extends BaseEntity {
	private int accessCardId;
	private LocalDateTime visitDateTime;

	public int getAccessCardId() {
		return accessCardId;
	}

	public void setAccessCardId(int accessCardId) {
		this.accessCardId = accessCardId;
	}

	public LocalDateTime getVisitDateTime() {
		return visitDateTime;
	}

	public void setVisitDateTime(LocalDateTime visitDateTime) {
		this.visitDateTime = visitDateTime;
	}

	@Override
	public String toString() {
		return "VisitRecord{" +
		       "id=" + id +
		       ", accessCardId=" + accessCardId +
		       ", visitDateTime=" + visitDateTime +
		       '}';
	}
}
