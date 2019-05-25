package com.mihailya.coursework.server.commands.adminCommands.commands;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.server.commands.adminCommands.AbstractAdminCommand;
import com.mihailya.coursework.server.commands.deviceCommands.commands.DeviceEmptyCommand;
import com.mihailya.coursework.server.commands.util.PageFiller;
import com.mihailya.coursework.server.managers.PageManager;
import com.mihailya.coursework.server.controllers.MainController;
import org.springframework.ui.Model;

import java.util.Map;

public class AdminEmptyCommand extends AbstractAdminCommand {
	@Override
	public String execute(Map<String, String> requestParams, Model model, AccessDevice accessDevice, Map<String, String> sessionParams) {
		if(sessionParams.get(MainController.SESSION_ADMIN_ID) == null) {
			return new DeviceEmptyCommand().execute(requestParams, model, accessDevice, sessionParams);
		}

		PageFiller.outputAdminPanelInfo(requestParams, model, accessDevice);
		return PageManager.getInstance().getPage(PageManager.PagesIds.ADMIN_DEFAULT_PAGE);
	}
}
