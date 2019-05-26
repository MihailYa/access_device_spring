package com.mihailya.coursework.server.commands.adminCommands.commands;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.accessDevice.data.entities.AccessCard;
import com.mihailya.coursework.server.commands.adminCommands.AbstractAdminCommand;
import com.mihailya.coursework.server.commands.util.EntityFiller;
import com.mihailya.coursework.server.controllers.MainController;
import com.mihailya.coursework.server.managers.PageManager;
import org.springframework.ui.Model;

import java.util.Map;

public class InsertAccessCardCommand extends AbstractAdminCommand {
	@Override
	public String execute(Map<String, String> requestParams, Model model, AccessDevice accessDevice, Map<String, String> sessionParams) {
		checkAdminSession(sessionParams);

		AccessCard accessCard = EntityFiller.fillAccessCardData(requestParams);
		if(accessCard == null) {
			throw new RuntimeException("Wrong input data");
		}

		accessDevice.getMemory()
		            .getAdminPanel()
		            .insertAccessCard(accessCard);

		outputAdminPanelInfo(requestParams, model, accessDevice);


		return PageManager.getInstance()
		                  .getPage(PageManager.PagesIds.ADMIN_PANEL_PAGE);
	}
}
