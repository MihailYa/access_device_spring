package com.mihailya.coursework.accessDevice.data.entities;

import java.time.LocalDateTime;

public class LockCardRecord extends BaseEntity {
	private int accessCardId;
	private LocalDateTime lockingDateTime;

	public int getAccessCardId() {
		return accessCardId;
	}

	public void setAccessCardId(int accessCardId) {
		this.accessCardId = accessCardId;
	}

	public LocalDateTime getLockingDateTime() {
		return lockingDateTime;
	}

	public void setLockingDateTime(LocalDateTime lockingDateTime) {
		this.lockingDateTime = lockingDateTime;
	}
}
