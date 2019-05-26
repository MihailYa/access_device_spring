package com.mihailya.coursework.accessDevice.util.managers;

import java.util.ResourceBundle;

public class DeviceMessagesManager {
	private static DeviceMessagesManager instance;

	public static DeviceMessagesManager getInstance() {
		if(instance == null) {
			instance = new DeviceMessagesManager();
		}
		return instance;
	}

	public static class MessagesIds {
		public static final String INSERT_CARD = "insertCard";
		public static final String ADDITIONAL_MESSAGE_INSERT_CARD = "additionalMessageInsertCard";
		public static final String ACCESS_DENIED = "accessDenied";
		public static final String NAME_INPUT_CODE = "nameInputCode";
		public static final String YOU_HAVE_TIME = "youHaveTime";
		public static final String DOOR_LOCKED = "doorLocked";
		public static final String ADDITIONAL_MESSAGE_YOU_HAVE_TIME = "additionalMessageYouHaveTime";
		public static final String WRONG_PASSWORD_ATTEMPTS_LEFT = "wrongPasswordAttemptsLeft";
		public static final String WRONG_PASSWORD_CARD_IS_LOCKED = "wrongPasswordCardIsLocked";
		public static final String ENCODED_ACCESS_CODE = "encodedAccessCode";

		public static final String INPUT_CONTROL_CODE_FOR_CONTROL_CODE_CHANGING =
				"inputControlCodeForControlCodeChanging";
		public static final String INPUT_NEW_CONTROL_CODE = "inputNewControlCode";
		public static final String CONTROL_CODE_SUCCESSFULLY_MODIFIED = "controlCodeSuccessfullyModified";

		public static final String INPUT_CONTROL_CODE_FOR_ACCESS_CODE_CHANGING =
				"inputControlCodeForAccessCodeChanging";
		public static final String INPUT_NEW_ACCESS_CODE = "inputNewAccessCode";
		public static final String ACCESS_CODE_SUCCESSFULLY_MODIFIED = "accessCodeSuccessfullyModified";

		public static final String ENCODED_CONTROL_CODE = "encodedControlCode";

		public static final String WRONG_CONTROL_CODE = "wrongControlCode";
	}


	private static final String BUNDLE_NAME = "deviceMessages";

	private ResourceBundle resourceBundle;

	private DeviceMessagesManager() {
		resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
	}

	public String getMessage(String messageId) {
		return resourceBundle.getString(messageId);
	}
}
