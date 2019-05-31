package com.mihailya.coursework.server.commands.adminCommands;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.server.commands.Command;
import com.mihailya.coursework.server.commands.util.PageFiller;
import com.mihailya.coursework.server.controllers.MainController;
import org.springframework.ui.Model;

import java.util.Map;

public abstract class AbstractAdminCommand implements Command {
	public abstract String execute(Map<String, String> requestParams, Model model,
	                               AccessDevice accessDevice, Map<String, String> sessionParams);

	protected void outputAdminPanelInfo(Map<String, String> requestParams, Model model,
	                                    AccessDevice accessDevice) {
		PageFiller.outputAdminPanelInfo(requestParams, model, accessDevice);
	}

	protected void checkAdminSession(Map<String, String> sessionParams) {
		Object adminId = sessionParams.get(MainController.SESSION_ADMIN_ID);
		if (adminId == null) {
			throw new RuntimeException("Admin session required for this page");
		}
	}

}
