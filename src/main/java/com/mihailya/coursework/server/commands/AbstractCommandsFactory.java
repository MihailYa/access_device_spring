package com.mihailya.coursework.server.commands;

import java.util.Map;

public abstract class AbstractCommandsFactory {
	abstract public Command getCommand(Map<String, String> requestParams);

}
