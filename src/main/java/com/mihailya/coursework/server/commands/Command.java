package com.mihailya.coursework.server.commands;

import com.mihailya.coursework.accessDevice.AccessDevice;
import org.springframework.ui.Model;

import java.util.Map;

public interface Command {
	String execute(Map<String, String> requestParams, Model model, AccessDevice accessDevice, Map<String, String> sessionParams);
}
