package com.mihailya.coursework.server.commands.adminCommands.commands;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.accessDevice.data.entities.AccessCard;
import com.mihailya.coursework.server.commands.adminCommands.AbstractAdminCommand;
import com.mihailya.coursework.server.managers.PageManager;
import com.mihailya.coursework.server.controllers.MainController;
import org.springframework.ui.Model;

import java.util.Map;

public class UpdateAccessCardCommand extends AbstractAdminCommand {
	@Override
	public String execute(Map<String, String> requestParams, Model model, AccessDevice accessDevice, Map<String, String> sessionParams) {
		Object adminId = sessionParams.get(MainController.SESSION_ADMIN_ID);
		if(adminId == null) {
			return new AdminEmptyCommand().execute(requestParams, model, accessDevice, sessionParams);
		}

		String accessCardId = requestParams.get(MainController.PARAM_ACCESS_CARD_ID);
		String personId = requestParams.get(MainController.PARAM_ACCESS_CARD_PERSON_ID);
		String scheduleId = requestParams.get(MainController.PARAM_ACCESS_CARD_SCHEDULE_ID);
		String isAccessCardLocked = requestParams.get(MainController.PARAM_IS_ACCESS_CARD_LOCKED);

		if(accessCardId != null && personId != null && scheduleId != null) {
			AccessCard accessCard = new AccessCard();
			accessCard.setId(Integer.valueOf(accessCardId));
			accessCard.setPersonId(Integer.valueOf(personId));
			accessCard.setScheduleId(Integer.valueOf(scheduleId));
			accessCard.setLocked(isAccessCardLocked != null);

			accessDevice.getMemory().getAdminPanel().updateAccessCard(accessCard);
		}

		outputAdminPanelInfo(requestParams, model, accessDevice);


		return PageManager.getInstance()
		                  .getPage(PageManager.PagesIds.ADMIN_PANEL_PAGE);
	}
}
