package com.mihailya.coursework.server.commands.deviceCommands.commands;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.accessDevice.data.entities.AccessCard;
import com.mihailya.coursework.server.commands.deviceCommands.AbstractDeviceCommand;
import com.mihailya.coursework.server.managers.PageManager;
import com.mihailya.coursework.server.controllers.MainController;
import org.springframework.ui.Model;

import java.util.Map;

public class AccessCardRecipientCommand extends AbstractDeviceCommand {
	@Override
	public String execute(Map<String, String> requestParams, Model model, AccessDevice accessDevice, Map<String, String> sessionParams) {
		String accessCardId = requestParams.get(MainController.PARAM_ACCESS_CARD_ID);

		if(accessCardId == null || accessCardId.isEmpty()) {
			return new DeviceEmptyCommand().execute(requestParams, model, accessDevice, sessionParams);
		}

		AccessCard accessCard = new AccessCard();
		accessCard.setId(Integer.valueOf(accessCardId));

		accessDevice.getAccessCardRecipient().insertCard(accessCard);

		outputAccessDevice(requestParams, model, accessDevice);

		return PageManager.getInstance()
		                  .getPage(PageManager.PagesIds.ACCESS_DEVICE_PAGE);
	}
}
