package com.mihailya.coursework.server.commands.deviceCommands.commands;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.ButtonsPanel;
import com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.buttons.ButtonHelper;
import com.mihailya.coursework.server.commands.deviceCommands.AbstractDeviceCommand;
import com.mihailya.coursework.server.managers.PageManager;
import com.mihailya.coursework.server.controllers.MainController;
import org.springframework.ui.Model;

import java.util.Map;

public class ButtonsPanelCommand extends AbstractDeviceCommand {

	@Override
	public String execute(Map<String, String> requestParams, Model model, AccessDevice accessDevice, Map<String, String> sessionParams) {
		String numberButton = requestParams.get(MainController.PARAM_NUMBER_BUTTON);
		String callButton = requestParams.get(MainController.PARAM_CALL_BUTTON);
		String callButtonPress = requestParams.get(MainController.PARAM_CALL_BUTTON_PRESS);
		String controlButton = requestParams.get(MainController.PARAM_CONTROL_BUTTON);

		ButtonsPanel buttonsPanel = accessDevice.getButtonsPanel();
		ButtonHelper buttonHelper = ButtonHelper.getInstance();
		if (numberButton != null) {
			buttonsPanel.clickButton(buttonHelper.getNumberButton(Integer.valueOf(numberButton)));
		}
		if (callButton != null) {
			buttonsPanel.clickButton(Button.CALL);
		}
		if(callButtonPress != null) {
			buttonsPanel.pressButton(Button.CALL);
		} else {
			buttonsPanel.releaseButton(Button.CALL);
		}

		if (controlButton != null) {
			buttonsPanel.clickButton(Button.CONTROL);
		}

		outputAccessDevice(requestParams, model, accessDevice);

		return PageManager.getInstance()
		                  .getPage(PageManager.PagesIds.ACCESS_DEVICE_PAGE);
	}
}
