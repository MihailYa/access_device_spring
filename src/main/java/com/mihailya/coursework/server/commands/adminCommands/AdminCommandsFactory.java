package com.mihailya.coursework.server.commands.adminCommands;

import com.mihailya.coursework.server.commands.AbstractCommandsFactory;
import com.mihailya.coursework.server.commands.ICommand;
import com.mihailya.coursework.server.commands.adminCommands.commands.AdminEmptyCommand;
import com.mihailya.coursework.server.commands.adminCommands.commands.AdminPanelCommand;
import com.mihailya.coursework.server.commands.adminCommands.commands.LogoutCommand;
import com.mihailya.coursework.server.commands.adminCommands.commands.UpdateAccessCardCommand;

import java.util.HashMap;
import java.util.Map;

public class AdminCommandsFactory extends AbstractCommandsFactory {
	public static final String PARAM_COMMAND = "command";

	private HashMap<String, AbstractAdminCommand> commands = new HashMap<String, AbstractAdminCommand>();

	public AdminCommandsFactory() {
		commands.put("adminPanelCommand", new AdminPanelCommand());
		commands.put("updateAccessCard", new UpdateAccessCardCommand());
		commands.put("logoutCommand", new LogoutCommand());
	}

	@Override
	public ICommand getCommand(Map<String, String> requestParams) {
		String requestCommand = requestParams.get(PARAM_COMMAND);

		AbstractAdminCommand command = commands.get(requestCommand);

		if(command == null) {
			command = new AdminEmptyCommand();
		}

		return command;
	}

}
