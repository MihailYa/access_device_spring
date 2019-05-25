package com.mihailya.coursework.server.commands.adminCommands.commands;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.server.commands.adminCommands.AbstractAdminCommand;
import com.mihailya.coursework.server.managers.PageManager;
import com.mihailya.coursework.server.controllers.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import java.util.Map;

public class AdminPanelCommand extends AbstractAdminCommand {

	private Logger log = LoggerFactory.getLogger(AdminPanelCommand.class.getName());

	@Override
	public String execute(Map<String, String> requestParams, Model model, AccessDevice accessDevice, Map<String, String> sessionParams) {
		log.info("Execute AdminPanelCommand command");

		Object adminId = sessionParams.get(MainController.SESSION_ADMIN_ID);

		if(adminId == null) {
			log.info("adminId == null. Return empty command");
			return new AdminEmptyCommand().execute(requestParams, model, accessDevice, sessionParams);
		}


		outputAdminPanelInfo(requestParams, model, accessDevice);


		return PageManager.getInstance()
		                  .getPage(PageManager.PagesIds.ADMIN_PANEL_PAGE);
	}
}
