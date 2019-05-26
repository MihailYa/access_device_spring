package com.mihailya.coursework.server.commands.adminCommands.commands;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.server.commands.adminCommands.AbstractAdminCommand;
import com.mihailya.coursework.server.controllers.MainController;
import com.mihailya.coursework.server.managers.PageManager;
import org.springframework.ui.Model;

import java.util.Map;

public class LogoutCommand extends AbstractAdminCommand {
	@Override
	public String execute(Map<String, String> requestParams, Model model, AccessDevice accessDevice, Map<String, String> sessionParams) {
		if (sessionParams.get(MainController.SESSION_ADMIN_ID) != null) {
			sessionParams.remove(MainController.SESSION_ADMIN_ID);
		}

		return PageManager.getInstance().getPage(PageManager.PagesIds.ACCESS_DEVICE_SERVLET);
	}
}
