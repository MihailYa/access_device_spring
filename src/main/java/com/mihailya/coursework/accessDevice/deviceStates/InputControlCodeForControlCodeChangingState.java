package com.mihailya.coursework.accessDevice.deviceStates;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import com.mihailya.coursework.accessDevice.util.CodeMasker;
import com.mihailya.coursework.accessDevice.util.DeviceConfigManager;
import com.mihailya.coursework.accessDevice.util.DeviceMessagesManager;

public class InputControlCodeForControlCodeChangingState extends AccessDeviceState {

	private final int controlCodeSize =
			DeviceConfigManager.getInstance()
			                   .getIntConfig(DeviceConfigManager.ConfigIds.CONTROL_CODE_SIZE);
	private StringBuilder controlCode = new StringBuilder(controlCodeSize);

	public InputControlCodeForControlCodeChangingState(AccessDevice parent) {
		super(parent);
		String inputControlCodeForControlCodeChangingMessage =
				DeviceMessagesManager.getInstance()
				                     .getMessage(DeviceMessagesManager.MessagesIds.INPUT_CONTROL_CODE_FOR_CONTROL_CODE_CHANGING);

		parent.updateMessage(inputControlCodeForControlCodeChangingMessage);
	}

	@Override
	public void onReleaseButton(Button button) {
		if (button == Button.CALL)
			parent.changeState(new InitState(parent));
	}


	@Override
	public void onButtonClick(Button button) {
		if(!button.isNumberButton())
			return;

		controlCode.append(button.getValue());

		if(controlCode.length() == controlCodeSize) {
			if(parent.getMemory().verifyControlCode(controlCode.toString())) {
				parent.changeState(new InputNewControlCodeState(parent));
			} else {
				String wrongControlCodeMessage =
						DeviceMessagesManager.getInstance()
						                     .getMessage(DeviceMessagesManager.MessagesIds.WRONG_CONTROL_CODE);

				parent.changeState(new InitState(parent, wrongControlCodeMessage));
			}
		} else {
			String encodedControlCodeMessage =
					DeviceMessagesManager.getInstance()
					                     .getMessage(DeviceMessagesManager.MessagesIds.ENCODED_CONTROL_CODE);
			parent.updateMessage(String.format(encodedControlCodeMessage, CodeMasker.maskCode(controlCode.length())));
		}
	}
}
