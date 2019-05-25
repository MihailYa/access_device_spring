package com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.buttons;

import java.util.HashMap;

public enum Button {
	BUTTON_0(0, 0),
	BUTTON_1(1, 1),
	BUTTON_2(2, 2),
	BUTTON_3(3, 3),
	BUTTON_4(4, 4),
	BUTTON_5(5, 5),
	BUTTON_6(6, 6),
	BUTTON_7(7, 7),
	BUTTON_8(8, 8),
	BUTTON_9(9, 9),
	CALL(-1, 10),
	CONTROL(-2, 11);

	private static HashMap<Integer, Button> buttonValueMap = new HashMap<>();
	private static HashMap<Integer, Button> buttonIndexMap = new HashMap<>();
	static {
		for (Button button : Button.values()) {
			buttonValueMap.put(button.value, button);
			buttonIndexMap.put(button.index, button);
		}
	}

	public static Button getButtonForValue(int value) {
		return buttonValueMap.get(value);
	}

	public static Button getButtonForIndex(int index) {
		return buttonIndexMap.get(index);
	}


	private int value;
	private int index;

	Button(int value, int index) {
		this.value = value;
		this.index = index;
	}

	public int getValue() {
		return value;
	}

	public int getIndex() {
		return index;
	}

	public boolean isNumberButton() {
		return getValue() >= 0;
	}

}
