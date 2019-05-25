package com.mihailya.coursework.server.config;

import com.mihailya.coursework.accessDevice.AccessDevice;
import com.mihailya.coursework.accessDevice.data.entities.Admin;
import com.mihailya.coursework.server.commands.AbstractCommandsFactory;
import com.mihailya.coursework.server.commands.adminCommands.AdminCommandsFactory;
import com.mihailya.coursework.server.commands.deviceCommands.DeviceCommandsFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.support.DefaultSessionAttributeStore;
import org.springframework.web.bind.support.SessionAttributeStore;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

	private static final String DATABASE_FILE_NAME = "/test.db";

	@Bean
	public TaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setThreadNamePrefix("accessDeviceThreadExecutor");
		executor.initialize();
		return executor;
	}

	@Bean
	public AccessDevice accessDevice() {
		return new AccessDevice(threadPoolTaskExecutor(), dataSource());
	}

	@Bean
	public AbstractCommandsFactory deviceCommandsFactory() {
		return new DeviceCommandsFactory();
	}

	@Bean
	public AbstractCommandsFactory adminCommandsFactory() {
		return new AdminCommandsFactory();
	}

	@Bean
	public DataSource dataSource() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		String databasePath = getClass().getResource(DATABASE_FILE_NAME)
		                                .getPath()
		                                .replace("%20", " ");

		String url = "jdbc:sqlite:" + databasePath;

		DriverManagerDataSource dataSource = new DriverManagerDataSource(url);
		dataSource.setDriverClassName("org.sqlite.JDBC");

		return dataSource;
	}

	@Bean
	public Admin admin() {
		return new Admin();
	}
}
