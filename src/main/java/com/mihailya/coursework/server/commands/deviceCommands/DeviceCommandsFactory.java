package com.mihailya.coursework.server.commands.deviceCommands;

import com.mihailya.coursework.server.commands.AbstractCommandsFactory;
import com.mihailya.coursework.server.commands.ICommand;
import com.mihailya.coursework.server.commands.deviceCommands.commands.AccessCardRecipientCommand;
import com.mihailya.coursework.server.commands.deviceCommands.commands.AdminLoginCommand;
import com.mihailya.coursework.server.commands.deviceCommands.commands.ButtonsPanelCommand;
import com.mihailya.coursework.server.commands.deviceCommands.commands.DeviceDefaultCommand;

import java.util.HashMap;
import java.util.Map;

public class DeviceCommandsFactory extends AbstractCommandsFactory {

	public static final String PARAM_COMMAND = "command";

	private HashMap<String, AbstractDeviceCommand> commands = new HashMap<>();

	public DeviceCommandsFactory() {
		commands.put("buttonsPanelCommand", new ButtonsPanelCommand());
		commands.put("accessCardRecipientCommand", new AccessCardRecipientCommand());
		commands.put("adminLoginCommand", new AdminLoginCommand());
	}

	@Override
	public ICommand getCommand(Map<String, String> requestParams) {
		String requestCommand = requestParams.get(PARAM_COMMAND);

		AbstractDeviceCommand command = commands.get(requestCommand);

		if(command == null) {
			command = new DeviceDefaultCommand();
		}

		return command;
	}

}
