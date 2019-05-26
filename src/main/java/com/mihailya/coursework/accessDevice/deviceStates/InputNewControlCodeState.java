package com.mihailya.coursework.accessDevice.deviceStates;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import com.mihailya.coursework.accessDevice.util.security.CodeMasker;
import com.mihailya.coursework.accessDevice.util.managers.DeviceConfigManager;
import com.mihailya.coursework.accessDevice.util.managers.DeviceMessagesManager;

public class InputNewControlCodeState extends AccessDeviceState {

	private final int controlCodeSize =
			DeviceConfigManager.getInstance()
			                   .getIntConfig(DeviceConfigManager.ConfigIds.CONTROL_CODE_SIZE);
	private StringBuilder newControlCode = new StringBuilder(controlCodeSize);

	public InputNewControlCodeState(AccessDevice parent) {
		super(parent);

		String inputNewControlCodeMessage =
				DeviceMessagesManager.getInstance()
				                     .getMessage(DeviceMessagesManager.MessagesIds.INPUT_NEW_CONTROL_CODE);

		parent.updateMessage(inputNewControlCodeMessage);
	}

	@Override
	public void onButtonClick(Button button) {
		if(!button.isNumberButton())
			return;

		newControlCode.append(button.getValue());

		if(newControlCode.length() == controlCodeSize) {
			parent.getMemory().modifyControlCode(newControlCode.toString());

			String controlCodeSuccessfullyModifiedMessage =
					DeviceMessagesManager.getInstance()
					                     .getMessage(DeviceMessagesManager.MessagesIds.CONTROL_CODE_SUCCESSFULLY_MODIFIED);

			parent.changeState(new DoorUnlockedState(parent, controlCodeSuccessfullyModifiedMessage));
		} else {
			String encodedControlCodeMessage =
					DeviceMessagesManager.getInstance()
					                     .getMessage(DeviceMessagesManager.MessagesIds.ENCODED_CONTROL_CODE);

			parent.updateMessage(String.format(encodedControlCodeMessage, CodeMasker.maskCode(newControlCode.length())));
		}
	}
}
