package com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel;

import com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.buttons.Button;

public interface IButtonsPanelEventsReceiver {
	void onButtonClick(Button button);
	void onPressButton(Button button);
	void onReleaseButton(Button button);
}
