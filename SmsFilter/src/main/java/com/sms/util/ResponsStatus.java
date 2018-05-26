package com.sms.util;

import java.util.HashMap;
import java.util.Map;

public class ResponsStatus {

	public static int DELIVERED = 200;

	private static Map<Integer, String> statusDescriptionMap = new HashMap<Integer, String>();

	static void fill() {
		statusDescriptionMap.put(DELIVERED, "Message is delivered");
	}

	public static String getDescription(int status) {
		return statusDescriptionMap.get(status);
	}
}
