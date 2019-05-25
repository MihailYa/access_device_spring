package com.mihailya.coursework.server.managers;

import java.util.ResourceBundle;

public class MessageManager {
	private static MessageManager instance = new MessageManager();

	public static MessageManager getInstance() {
		return instance;
	}

	private static final String BUNDLE_NAME = "messages";

	public static class MessagesIds {
		public static final String WRONG_LOGIN_OR_PASSWORD = "wrongLoginOrPassword";
	}

	private ResourceBundle resourceBundle;

	private MessageManager() {
		resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
	}


	public String getMessage(String id) {
		return resourceBundle.getString(id);
	}
}
