package com.mihailya.coursework.server.controllers;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.accessDevice.data.entities.AccessCard;
import com.mihailya.coursework.accessDevice.data.entities.Admin;
import com.mihailya.coursework.accessDevice.data.util.SqlStatementsManager;
import com.mihailya.coursework.server.commands.AbstractCommandsFactory;
import com.mihailya.coursework.server.commands.ICommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.DefaultSessionAttributeStore;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;


@Controller
@SessionAttributes("admin")
public class MainController {
	private Logger log = LoggerFactory.getLogger(MainController.class.getName());

	public static final String PARAM_NUMBER_BUTTON = "numberButton";
	public static final String PARAM_CALL_BUTTON = "callButton";
	public static final String PARAM_CALL_BUTTON_PRESS = "callButtonPress";
	public static final String PARAM_CONTROL_BUTTON = "controlButton";

	public static final String PARAM_ADMIN_LOGIN = "adminLogin";
	public static final String PARAM_ADMIN_PASSWORD = "adminPassword";

	public static final String PARAM_ACCESS_CARD_ID = "accessCardId";

	public static final String OUT_PARAM_MESSAGE = "message";
	public static final String OUT_PARAM_IS_DOOR_LOCKED = "isDoorLocked";
	public static final String OUT_PARAM_IS_BELL_RINGING = "isBellRinging";
	public static final String OUT_PARAM_CALL_BUTTON_CHECKED = "callButtonChecked";
	public static final String OUT_PARAM_INSERTED_CARD_ID = "insertedCardId";

	public static final String OUT_LOGIN_ERROR_MESSAGE = "loginErrorMessage";

	public static final String PARAM_ACCESS_CARD_PERSON_ID = "accessCardPersonId";
	public static final String PARAM_ACCESS_CARD_SCHEDULE_ID = "accessCardScheduleId";
	public static final String PARAM_ACCESS_CARD_PERSON_NAME = "accessCardPersonName";
	public static final String PARAM_ACCESS_CARD_PERSON_SURNAME = "accessCardPersonSurname";
	public static final String PARAM_ACCESS_CARD_SCHEDULE_BEGIN_TIME = "accessCardScheduleBeginTime";
	public static final String PARAM_ACCESS_CARD_SCHEDULE_END_TIME = "accessCardScheduleEndTime";
	public static final String PARAM_IS_ACCESS_CARD_LOCKED = "isAccessCardLocked";

	public static final String OUT_PARAM_ACCESS_CARDS = "accessCards";
	public static final String OUT_PARAM_VISIT_RECORDS = "visitRecords";
	public static final String OUT_PARAM_LOCK_CARD_RECORDS = "lockCardRecords";
	public static final String OUT_PARAM_ERROR_MESSAGE = "errorMessage";

	public static final String SESSION_ADMIN_ID = "sessionAdminId";

	private AccessDevice accessDevice;

	private AbstractCommandsFactory deviceCommandsFactory;
	private AbstractCommandsFactory adminCommandsFactory;

	@Autowired
	public MainController(AccessDevice accessDevice,
	                      AbstractCommandsFactory deviceCommandsFactory,
	                      AbstractCommandsFactory adminCommandsFactory) {
		this.accessDevice = accessDevice;
		this.deviceCommandsFactory = deviceCommandsFactory;
		this.adminCommandsFactory = adminCommandsFactory;

		log.info("New MainController was created");
	}


	@RequestMapping(value = "/AccessDevice", method = {RequestMethod.GET, RequestMethod.POST})
	public String accessDeviceRequestsHandler(@RequestParam Map<String, String> allRequestParams,
	                                          Model model) {

		ICommand command = deviceCommandsFactory.getCommand(allRequestParams);

		Map<String, String> sessionParams = new HashMap<>();
		String page = command.execute(allRequestParams, model, accessDevice, sessionParams);

		String adminId = sessionParams.get(MainController.SESSION_ADMIN_ID);
		if (adminId != null) {
			Admin admin = new Admin();
			admin.setId(Integer.valueOf(adminId));
			model.addAttribute("admin", admin);
		}

		return page;
	}

	@RequestMapping(value = "/AdminPanelServlet", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminPanelRequestsHandler(@SessionAttribute("admin") Admin admin,
	                                        @RequestParam Map<String, String> allRequestParams,
	                                        Model model,
	                                        WebRequest request,
	                                        DefaultSessionAttributeStore store,
	                                        SessionStatus status) {
		ICommand command = adminCommandsFactory.getCommand(allRequestParams);

		Map<String, String> sessionParams = new HashMap<>();
		sessionParams.put(MainController.SESSION_ADMIN_ID, Integer.toString(admin.getId()));

		String page = command.execute(allRequestParams, model, accessDevice, sessionParams);

		String adminId = sessionParams.get(MainController.SESSION_ADMIN_ID);
		if (adminId == null) {
			status.setComplete();
			store.cleanupAttribute(request, "admin");
		}

		return page;
	}
}
