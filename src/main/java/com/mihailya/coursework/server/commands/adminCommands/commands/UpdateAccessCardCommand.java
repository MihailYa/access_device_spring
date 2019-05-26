package com.mihailya.coursework.server.commands.adminCommands.commands;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.accessDevice.data.entities.AccessCard;
import com.mihailya.coursework.accessDevice.data.entities.Person;
import com.mihailya.coursework.accessDevice.data.entities.Schedule;
import com.mihailya.coursework.server.commands.adminCommands.AbstractAdminCommand;
import com.mihailya.coursework.server.commands.util.EntityFiller;
import com.mihailya.coursework.server.managers.PageManager;
import com.mihailya.coursework.server.controllers.MainController;
import org.springframework.ui.Model;

import java.time.LocalTime;
import java.util.Map;

public class UpdateAccessCardCommand extends AbstractAdminCommand {
	@Override
	public String execute(Map<String, String> requestParams, Model model, AccessDevice accessDevice, Map<String, String> sessionParams) {
		checkAdminSession(sessionParams);

		try {
			AccessCard accessCard = EntityFiller.fillAccessCard(requestParams);

			if (accessCard == null) {
				throw new RuntimeException("Wrong input data");
			}

			accessDevice.getMemory()
			            .getAdminPanel()
			            .updateAccessCard(accessCard);
		} catch (Exception e) {
			model.addAttribute(MainController.OUT_PARAM_ERROR_MESSAGE, "Error: " + e.getMessage());
		}

		outputAdminPanelInfo(requestParams, model, accessDevice);

		return PageManager.getInstance()
		                  .getPage(PageManager.PagesIds.ADMIN_PANEL_PAGE);
	}
}
