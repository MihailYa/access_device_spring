package com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.buttons;

public class ButtonHelper {
	private static ButtonHelper instance;

	public static ButtonHelper getInstance() {
		if(instance == null) {
			instance = new ButtonHelper();
		}

		return instance;
	}

	public Button getButton(String buttonName) {
		return Button.valueOf(buttonName);
	}

	public Button getNumberButton(int val) {
		return Button.getButtonForValue(val);
	}
}
