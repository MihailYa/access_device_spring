package com.mihailya.coursework.accessDevice.util;

import java.util.ResourceBundle;

public class DeviceConfigManager {
	private static DeviceConfigManager instance;

	public static DeviceConfigManager getInstance() {
		if(instance == null)
			instance = new DeviceConfigManager();

		return instance;
	}

	public static class ConfigIds {
		public static final String ACCESS_CODE_SIZE = "accessCodeSize";
		public static final String CONTROL_CODE_SIZE = "controlCodeSize";
		public static final String ACCESS_CODE_INPUT_MAX_ATTEMPTS = "accessCodeInputMaxAttempts";
		public static final String DOOR_UNLOCKED_SECONDS = "doorUnlockedSeconds";
		public static final String BELL_RINGING_SECONDS = "bellRingingSeconds";

	}

	private static final String BUNDLE_NAME = "deviceConfig";

	private ResourceBundle resourceBundle;

	private DeviceConfigManager() {
		resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
	}

	public int getIntConfig(String configId) {
		return Integer.valueOf(resourceBundle.getString(configId));
	}

	public String getConfig(String messageId) {
		return resourceBundle.getString(messageId);
	}
}
