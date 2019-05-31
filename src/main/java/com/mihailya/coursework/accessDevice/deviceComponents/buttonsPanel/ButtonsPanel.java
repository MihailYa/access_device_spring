package com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel;

import com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.buttons.Button;

public class ButtonsPanel {
	private ButtonsPanelEventsReceiver buttonsPanelEventsReceiver;
	private boolean[] isButtonPressed;

	public ButtonsPanel(ButtonsPanelEventsReceiver buttonsPanelEventsReceiver) {
		this.buttonsPanelEventsReceiver = buttonsPanelEventsReceiver;
		isButtonPressed = new boolean[Button.values().length];
	}

	public void clickButton(Button button) {
		buttonsPanelEventsReceiver.onButtonClick(button);
	}

	public void pressButton(Button button) {
		int buttonIndex = button.getIndex();
		if(!isButtonPressed[buttonIndex]) {
			isButtonPressed[buttonIndex] = true;
			buttonsPanelEventsReceiver.onPressButton(button);
		}
	}

	public void releaseButton(Button button) {
		int buttonIndex = button.getIndex();
		if(isButtonPressed[buttonIndex]) {
			isButtonPressed[buttonIndex] = false;
			buttonsPanelEventsReceiver.onReleaseButton(button);
		}
	}

	public boolean isButtonPressed(Button button) {
		int buttonIndex = button.getIndex();
		return isButtonPressed[buttonIndex];
	}
}
