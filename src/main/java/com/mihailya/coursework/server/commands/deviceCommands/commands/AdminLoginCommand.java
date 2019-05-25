package com.mihailya.coursework.server.commands.deviceCommands.commands;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.accessDevice.data.entities.Admin;
import com.mihailya.coursework.server.commands.deviceCommands.AbstractDeviceCommand;
import com.mihailya.coursework.server.commands.util.PageFiller;
import com.mihailya.coursework.server.managers.MessageManager;
import com.mihailya.coursework.server.managers.PageManager;
import com.mihailya.coursework.server.controllers.MainController;
import org.springframework.ui.Model;

import java.util.Map;

public class AdminLoginCommand extends AbstractDeviceCommand {

	@Override
	public String execute(Map<String, String> requestParams, Model model, AccessDevice accessDevice, Map<String, String> sessionParams) {
		String login = requestParams.get(MainController.PARAM_ADMIN_LOGIN);
		String password = requestParams.get(MainController.PARAM_ADMIN_PASSWORD);

		if(login != null || password != null) {
			Admin admin = new Admin();
			admin.setLogin(login);
			admin.setPassword(password);

			if(accessDevice.getMemory().verifyAdmin(admin)) {
				sessionParams.put(MainController.SESSION_ADMIN_ID, Integer.toString(admin.getId()));

				return PageManager.getInstance().getPage(PageManager.PagesIds.ADMIN_PANEL_SERVLET);
			} else {

				outputAccessDevice(requestParams, model, accessDevice);
				PageFiller.outputLoginErrorMessage(requestParams, model,
				                                   MessageManager.getInstance().getMessage(MessageManager.MessagesIds.WRONG_LOGIN_OR_PASSWORD));

				return PageManager.getInstance().getPage(PageManager.PagesIds.ACCESS_DEVICE_PAGE);
			}
		}

		return new DeviceEmptyCommand().execute(requestParams, model, accessDevice, sessionParams);
	}
}
