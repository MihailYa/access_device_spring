package com.mihailya.coursework.server.commands.deviceCommands.commands;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.server.commands.deviceCommands.AbstractDeviceCommand;
import com.mihailya.coursework.server.commands.util.PageFiller;
import com.mihailya.coursework.server.managers.PageManager;
import org.springframework.ui.Model;

import java.util.Map;

public class DeviceDefaultCommand extends AbstractDeviceCommand {
	@Override
	public String execute(Map<String, String> requestParams, Model model, AccessDevice accessDevice, Map<String, String> sessionParams) {
		PageFiller.outputAccessDevice(requestParams, model, accessDevice);

		return PageManager.getInstance().getPage(PageManager.PagesIds.DEFAULT_PAGE);
	}
}
