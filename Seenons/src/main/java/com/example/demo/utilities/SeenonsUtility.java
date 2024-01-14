package com.example.demo.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeenonsUtility {
	public static String getFomattedDateInString(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd (EEEE)");
		return date.format(formatter);
	}

	public static String convertUppercaseToMixedCase(String uppercaseString) {
		if (uppercaseString == null || uppercaseString.isEmpty()) {
			return uppercaseString;
		}
		return Character.toUpperCase(uppercaseString.charAt(0)) + uppercaseString.substring(1).toLowerCase();
	}
}
