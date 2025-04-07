package jkz.lab4.Helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Debug {
	private static final boolean debug = true;

	private static String getCurrentTimeStamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	}

	public static void debug(String... message) {
		if (debug) {
			print(message);
		}
	}

	public static void print(String... message) {
		String messageString = String.join(" ", message);
		System.out.println(Colors.GREEN + getCurrentTimeStamp() + ": " + Colors.RESET + messageString);
	}
}
