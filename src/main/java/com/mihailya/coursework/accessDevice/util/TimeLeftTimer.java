package com.mihailya.coursework.accessDevice.util;

import org.springframework.core.task.TaskExecutor;

public class TimeLeftTimer {

	private volatile int timeLeftSeconds;
	private StopableRunnable timerRunnable;
	private TaskExecutor taskExecutor;

	public TimeLeftTimer(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public void start(int seconds) {
		if(seconds < timeLeftSeconds) {
			return;
		}
		if(timerRunnable != null) {
			timerRunnable.stop();
		}

		timeLeftSeconds = seconds;

		timerRunnable =
		new StopableRunnable() {
			@Override
			public void run() {
				while (timeLeftSeconds != 0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(isStoped) {
						return;
					}
					synchronized (TimeLeftTimer.this) {
						--timeLeftSeconds;
					}
				}
			}
		};

		taskExecutor.execute(timerRunnable);
	}

	public void stop() {
		if(timerRunnable != null) {
			timerRunnable.stop();
			timerRunnable = null;
		}
		timeLeftSeconds = 0;
	}

	public int getTimeLeftSeconds() {
		return timeLeftSeconds;
	}
}
