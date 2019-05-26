package com.mihailya.coursework.server.commands.adminCommands.commands;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.accessDevice.data.entities.AccessCard;
import com.mihailya.coursework.server.commands.adminCommands.AbstractAdminCommand;
import com.mihailya.coursework.server.commands.util.EntityFiller;
import com.mihailya.coursework.server.controllers.MainController;
import com.mihailya.coursework.server.managers.PageManager;
import org.springframework.ui.Model;

import java.util.Map;

public class DeleteAccessCardCommand extends AbstractAdminCommand {
	@Override
	public String execute(Map<String, String> requestParams, Model model, AccessDevice accessDevice, Map<String, String> sessionParams) {
		checkAdminSession(sessionParams);

		String accessCardId = requestParams.get(MainController.PARAM_ACCESS_CARD_ID);
		try {

			if (accessCardId == null) {
				throw new RuntimeException("Wrong input data");
			}

			int accessCardIdInt = Integer.parseInt(accessCardId);

			AccessCard accessCard = new AccessCard();
			accessCard.setId(accessCardIdInt);
			accessDevice.getMemory()
			            .getAdminPanel()
			            .deleteAccessCard(accessCard);
		} catch (Exception e) {
			model.addAttribute(MainController.OUT_PARAM_ERROR_MESSAGE, "Error: " + e.getMessage());
		}

		outputAdminPanelInfo(requestParams, model, accessDevice);

		return PageManager.getInstance()
		                  .getPage(PageManager.PagesIds.ADMIN_PANEL_PAGE);
	}
}
