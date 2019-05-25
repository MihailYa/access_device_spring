package com.mihailya.coursework.accessDevice.data.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class Converter {

	public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), TimeZone.getDefault().toZoneId());
	}

	public static Timestamp toTimestamp(LocalDateTime localDateTime) {
		return Timestamp.valueOf(localDateTime);
	}


}
