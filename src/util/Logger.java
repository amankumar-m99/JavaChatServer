package util;

import java.util.Calendar;

import appdata.AppStaticData;

public class Logger {

	private static Calendar calendar = Calendar.getInstance();
	public static void log(String text) {
		String currentText = AppStaticData.SERVER_LOGS_PROPERTY.getValue().trim();
		String newText = currentText + System.lineSeparator() + String.valueOf(calendar.getTime()) + " | " + text;
		AppStaticData.SERVER_LOGS_PROPERTY.setValue(newText);
	}
}
