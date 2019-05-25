package com.mihailya.coursework.server.commands.util;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.accessDevice.data.entities.AccessCard;
import com.mihailya.coursework.accessDevice.data.entities.LockCardRecord;
import com.mihailya.coursework.accessDevice.data.entities.VisitRecord;
import com.mihailya.coursework.accessDevice.deviceComponents.AdminPanel;
import com.mihailya.coursework.accessDevice.deviceComponents.buttonsPanel.buttons.Button;
import com.mihailya.coursework.server.controllers.MainController;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public class PageFiller {
	public static void outputAccessDevice(Map<String, String> requestParams, Model model,
	                                      AccessDevice accessDevice) {
		model.addAttribute(MainController.OUT_PARAM_MESSAGE, accessDevice.getScreenMessage());

		boolean isDoorLocked = accessDevice.isDoorLocked();
		model.addAttribute(MainController.OUT_PARAM_IS_DOOR_LOCKED, isDoorLocked);
		boolean isBellRinging = accessDevice.isBellRinging();
		model.addAttribute(MainController.OUT_PARAM_IS_BELL_RINGING, isBellRinging);

		AccessCard insertedCard = accessDevice.getAccessCardRecipient()
		                                      .getInsertedCard();
		model.addAttribute(MainController.OUT_PARAM_INSERTED_CARD_ID,
		                     insertedCard != null
				                     ? insertedCard.getId()
				                     : "");

		if (accessDevice.getButtonsPanel()
		                .isButtonPressed(Button.CALL)) {
			model.addAttribute(MainController.OUT_PARAM_CALL_BUTTON_CHECKED, "checked");
		}

		int refreshSeconds = accessDevice.getStateRefreshTimerSeconds();
		if (refreshSeconds != 0) {
			model.addAttribute("refresh", refreshSeconds);
		}
	}

	public static void outputLoginErrorMessage(Map<String, String> requestParams, Model model,
	                                           String loginErrorMessage) {
		model.addAttribute(MainController.OUT_LOGIN_ERROR_MESSAGE, loginErrorMessage);
	}

	public static void outputAdminPanelInfo(Map<String, String> requestParams, Model model,
	                                        AccessDevice accessDevice) {
		AdminPanel adminPanel = accessDevice.getMemory()
		                                    .getAdminPanel();

		List<AccessCard> accessCards = adminPanel.getAllAccessCards();
		model.addAttribute(MainController.OUT_PARAM_ACCESS_CARDS, accessCards);

		List<VisitRecord> visitRecords = adminPanel.getAllVisitRecords();
		model.addAttribute(MainController.OUT_PARAM_VISIT_RECORDS, visitRecords);

		List<LockCardRecord> lockCardRecords = adminPanel.getAllLockCardRecords();
		model.addAttribute(MainController.OUT_PARAM_LOCK_CARD_RECORDS, lockCardRecords);
	}
}
