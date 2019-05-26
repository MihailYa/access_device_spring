package com.mihailya.coursework.accessDevice.deviceStates;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import com.mihailya.coursework.accessDevice.util.managers.DeviceConfigManager;
import com.mihailya.coursework.accessDevice.util.managers.DeviceMessagesManager;
import org.springframework.core.task.TaskExecutor;

public class DoorUnlockedState extends AccessDeviceState {

	private final int doorUnlockedSeconds =
			DeviceConfigManager.getInstance()
			                   .getIntConfig(DeviceConfigManager.ConfigIds.DOOR_UNLOCKED_SECONDS);
	private volatile boolean isTimerStopped = false;

	public DoorUnlockedState(AccessDevice parent) {
		super(parent);
		unlockDoor();

		startTimer();
	}

	public DoorUnlockedState(AccessDevice parent, String additionalMessage) {
		super(parent);
		parent.setDoorLocked(false);
		String youHaveTimeMessage =
				DeviceMessagesManager.getInstance()
				                     .getMessage(DeviceMessagesManager.MessagesIds.ADDITIONAL_MESSAGE_YOU_HAVE_TIME);

		parent.updateMessage(String.format(youHaveTimeMessage, additionalMessage, doorUnlockedSeconds));

		startTimer();
	}

	private void unlockDoor() {
		parent.setDoorLocked(false);

		String youHaveTimeMessage =
				DeviceMessagesManager.getInstance()
				                     .getMessage(DeviceMessagesManager.MessagesIds.YOU_HAVE_TIME);

		parent.updateMessage(String.format(youHaveTimeMessage, doorUnlockedSeconds));
	}

	private void startTimer() {

		//TODO: It was sync
		parent.getDoorTimer()
		      .start(doorUnlockedSeconds);

		TaskExecutor taskExecutor = parent.getTaskExecutor();
		taskExecutor
				.execute(() -> {
					try {
						Thread.sleep(doorUnlockedSeconds * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if (isTimerStopped)
						return;

					synchronized (parent) {
						if (!parent.isDoorLocked()) {
							parent.setDoorLocked(true);
							String doorLocked =
									DeviceMessagesManager.getInstance()
									                     .getMessage(DeviceMessagesManager.MessagesIds.DOOR_LOCKED);

							parent.changeState(new InitState(parent, doorLocked));
						}
					}
				});
	}

	@Override
	public void onPressButton(Button button) {
		if (button == Button.CALL) {
			isTimerStopped = true;
			parent.getDoorTimer()
			      .stop();
			if (!parent.isDoorLocked())
				parent.setDoorLocked(true);
			parent.changeState(new InputControlCodeForControlCodeChangingState(parent));
		}
	}

	@Override
	public void onButtonClick(Button button) {
		if (button == Button.CONTROL) {
			isTimerStopped = true;
			parent.getDoorTimer()
			      .stop();
			if (!parent.isDoorLocked())
				parent.setDoorLocked(true);
			parent.changeState(new InputControlCodeForAccessCodeChanging(parent));
		}
	}
}
