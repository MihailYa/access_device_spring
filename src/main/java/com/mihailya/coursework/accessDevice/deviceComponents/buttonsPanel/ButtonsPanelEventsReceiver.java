package com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel;

import com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.buttons.Button;

public interface ButtonsPanelEventsReceiver {
	void onButtonClick(Button button);
	void onPressButton(Button button);
	void onReleaseButton(Button button);
}
