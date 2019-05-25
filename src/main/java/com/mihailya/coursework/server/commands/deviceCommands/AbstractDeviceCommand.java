package com.mihailya.coursework.server.commands.deviceCommands;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.server.commands.ICommand;
import com.mihailya.coursework.server.commands.util.PageFiller;
import org.springframework.ui.Model;

import java.util.Map;

public abstract class AbstractDeviceCommand implements ICommand {

	protected void outputAccessDevice(Map<String, String> requestParams, Model model,
	                                  AccessDevice accessDevice) {
		PageFiller.outputAccessDevice(requestParams, model, accessDevice);
	}
}
