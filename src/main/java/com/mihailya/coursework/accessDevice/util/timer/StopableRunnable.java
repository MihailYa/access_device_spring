package com.mihailya.coursework.accessDevice.util.timer;

public abstract class StopableRunnable implements Runnable {
	protected boolean isStoped = false;

	public void stop() {
		isStoped = true;
	}
}
