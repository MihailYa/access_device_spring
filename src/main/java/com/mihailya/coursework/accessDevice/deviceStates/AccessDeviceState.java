package com.mihailya.coursework.accessDevice.deviceStates;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.accessDevice.data.entities.AccessCard;
import com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.buttons.Button;

public abstract class AccessDeviceState {
	protected final AccessDevice parent;

	public AccessDeviceState(AccessDevice parent) {
		this.parent = parent;
	}

	public void onCardInserted(AccessCard accessCard) {}

	public void onButtonClick(Button button) {}

	public void onPressButton(Button button) {}

	public void onReleaseButton(Button button) {}
}
