package com.mihailya.coursework.server.commands.adminCommands;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.server.commands.ICommand;
import com.mihailya.coursework.server.commands.util.PageFiller;
import org.springframework.ui.Model;

import java.util.Map;

public abstract class AbstractAdminCommand implements ICommand {
	public abstract String execute(Map<String, String> requestParams, Model model,
	                               AccessDevice accessDevice, Map<String, String> sessionParams);

	protected void outputAdminPanelInfo(Map<String, String> requestParams, Model model,
	                                    AccessDevice accessDevice) {
		PageFiller.outputAdminPanelInfo(requestParams, model, accessDevice);
	}
}
