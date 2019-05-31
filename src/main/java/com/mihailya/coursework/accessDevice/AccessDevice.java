package com.mihailya.coursework.accessDevice;

import com.mihailya.coursework.accessDevice.data.entities.AccessCard;
import com.mihailya.coursework.accessDevice.deviceComponents.Memory;
import com.mihailya.coursework.accessDevice.deviceComponents.accessCardRecipient.AccessCardRecipient;
import com.mihailya.coursework.accessDevice.deviceComponents.accessCardRecipient.CardDataReceiver;
import com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.ButtonsPanel;
import com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.ButtonsPanelEventsReceiver;
import com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import com.mihailya.coursework.accessDevice.deviceStates.AccessDeviceState;
import com.mihailya.coursework.accessDevice.deviceStates.InitState;
import com.mihailya.coursework.accessDevice.util.timer.TimeLeftTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import javax.sql.DataSource;


public class AccessDevice implements ButtonsPanelEventsReceiver, CardDataReceiver {

	private Logger log = LoggerFactory.getLogger(AccessDevice.class.getName());

	private AccessDeviceState accessDeviceState = new InitState(this);
	private String screenMessage;
	private ButtonsPanel buttonsPanel = new ButtonsPanel(this);
	private AccessCardRecipient accessCardRecipient = new AccessCardRecipient(this);
	private boolean isDoorLocked = true;
	private boolean isBellRinging = false;
	private TimeLeftTimer bellTimer;
	private TimeLeftTimer doorTimer;
	private Memory memory;

	private OnAccessDeviceEvent onAccessDeviceEvent;

	private TaskExecutor taskExecutor;

	public AccessDevice(TaskExecutor taskExecutor, DataSource dataSource) {
		this.taskExecutor = taskExecutor;
		bellTimer = new TimeLeftTimer(taskExecutor);
		doorTimer = new TimeLeftTimer(taskExecutor);
		memory = new Memory(taskExecutor, dataSource);
	}

	@Override
	public void onCardInserted(AccessCard accessCard) {
		log.debug("Card inserted: " + accessCard);
		accessDeviceState.onCardInserted(accessCard);
	}

	@Override
	public void onButtonClick(Button button) {
		log.debug("Button clicked: " + button);
		accessDeviceState.onButtonClick(button);
	}

	@Override
	public void onPressButton(Button button) {
		log.debug("Button pressed: " + button);
		accessDeviceState.onPressButton(button);
	}

	@Override
	public void onReleaseButton(Button button) {
		log.debug("Button released: " + button);
		accessDeviceState.onReleaseButton(button);
	}

	public void updateMessage(String screenMessage) {
		log.debug("Message updated: " + screenMessage);
		this.screenMessage = screenMessage;
	}

	public void changeState(AccessDeviceState newState) {
		accessDeviceState = newState;
		log.debug("Change state to " + newState.getClass()
		                                        .getSimpleName());
	}

	public String getScreenMessage() {
		return screenMessage;
	}

	public ButtonsPanel getButtonsPanel() {
		return buttonsPanel;
	}

	public AccessCardRecipient getAccessCardRecipient() {
		return accessCardRecipient;
	}

	public Memory getMemory() {
		return memory;
	}

	public boolean isDoorLocked() {
		return isDoorLocked;
	}

	public boolean isBellRinging() {
		return isBellRinging;
	}

	public void setDoorLocked(boolean doorLocked) {
		log.debug("Set door locked: " + doorLocked);
		isDoorLocked = doorLocked;
		if (onAccessDeviceEvent != null)
			onAccessDeviceEvent.onUpdateDoorLocked(isDoorLocked);
	}

	public void setBellRinging(boolean bellRinging) {
		log.debug("Set bell ringing: " + bellRinging);
		isBellRinging = bellRinging;

		if (onAccessDeviceEvent != null)
			onAccessDeviceEvent.onUpdateBellRinging(isBellRinging);
	}

	public int getStateRefreshTimerSeconds() {
		int bellTime = bellTimer.getTimeLeftSeconds();
		int doorTime = doorTimer.getTimeLeftSeconds();

		if (bellTime == 0) {
			return doorTime;
		}

		if (doorTime == 0) {
			return bellTime;
		}

		return bellTime < doorTime ? bellTime : doorTime;
	}

	public TimeLeftTimer getBellTimer() {
		return bellTimer;
	}

	public TimeLeftTimer getDoorTimer() {
		return doorTimer;
	}

	public void setOnAccessDeviceEvent(OnAccessDeviceEvent onAccessDeviceEvent) {
		this.onAccessDeviceEvent = onAccessDeviceEvent;
	}

	public static interface OnAccessDeviceEvent {
		void onUpdateDoorLocked(boolean isDoorLocked);

		void onUpdateBellRinging(boolean isBellRinging);
	}

	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}
}
