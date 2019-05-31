package com.mihailya.coursework.server.commands.adminCommands;

import com.mihailya.coursework.server.commands.AbstractCommandsFactory;
import com.mihailya.coursework.server.commands.Command;
import com.mihailya.coursework.server.commands.adminCommands.commands.*;

import java.util.HashMap;
import java.util.Map;

public class AdminCommandsFactory extends AbstractCommandsFactory {
	public static final String PARAM_COMMAND = "command";

	private HashMap<String, AbstractAdminCommand> commands = new HashMap<String, AbstractAdminCommand>();

	public AdminCommandsFactory() {
		commands.put("adminPanelCommand", new AdminPanelCommand());
		commands.put("updateAccessCard", new UpdateAccessCardCommand());
		commands.put("insertAccessCard", new InsertAccessCardCommand());
		commands.put("deleteAccessCard", new DeleteAccessCardCommand());
		commands.put("logoutCommand", new LogoutCommand());
	}

	@Override
	public Command getCommand(Map<String, String> requestParams) {
		String requestCommand = requestParams.get(PARAM_COMMAND);

		AbstractAdminCommand command = commands.get(requestCommand);

		if(command == null) {
			command = new AdminDefaultCommand();
		}

		return command;
	}

}
