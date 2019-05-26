package com.mihailya.coursework.accessDevice.deviceStates;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import com.mihailya.coursework.accessDevice.util.security.CodeMasker;
import com.mihailya.coursework.accessDevice.util.managers.DeviceConfigManager;
import com.mihailya.coursework.accessDevice.util.managers.DeviceMessagesManager;

public class InputNewAccessCodeState extends AccessDeviceState {

	private final int accessCodeSize = DeviceConfigManager.getInstance()
	                                                      .getIntConfig(DeviceConfigManager.ConfigIds.ACCESS_CODE_SIZE);
	private StringBuilder newAccessCode = new StringBuilder(accessCodeSize);

	public InputNewAccessCodeState(AccessDevice parent) {
		super(parent);

		String inputNewControlCodeMessage =
				DeviceMessagesManager.getInstance()
				                     .getMessage(DeviceMessagesManager.MessagesIds.INPUT_NEW_ACCESS_CODE);

		parent.updateMessage(inputNewControlCodeMessage);
	}

	@Override
	public void onButtonClick(Button button) {
		if(!button.isNumberButton())
			return;

		newAccessCode.append(button.getValue());

		if(newAccessCode.length() == accessCodeSize) {
			parent.getMemory().modifyAccessCode(newAccessCode.toString());

			String accessCodeSuccessfullyModifiedMessage =
					DeviceMessagesManager.getInstance()
					                     .getMessage(DeviceMessagesManager.MessagesIds.ACCESS_CODE_SUCCESSFULLY_MODIFIED);

			parent.changeState(new DoorUnlockedState(parent, accessCodeSuccessfullyModifiedMessage));
		} else {
			String encodedAccessCodeMessage =
					DeviceMessagesManager.getInstance()
					                     .getMessage(DeviceMessagesManager.MessagesIds.ENCODED_ACCESS_CODE);

			parent.updateMessage(String.format(encodedAccessCodeMessage, CodeMasker.maskCode(newAccessCode.length())));
		}
	}
}
