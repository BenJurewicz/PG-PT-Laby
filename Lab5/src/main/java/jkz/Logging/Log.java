package jkz.Logging;

import java.util.Arrays;
import java.util.List;

public class Log {
	private static final boolean debug = true;
	static String debugPrefix = Colors.BLUE + "DEBUG: " + Colors.RESET;

	public static void print(String... message) {
		String messageString = String.join(" ", message);
		System.out.print(messageString);
	}

	public static void println(String... message) {
		String[] msg = Arrays.copyOf(message, message.length + 1);
		msg[msg.length - 1] = "\n";
		print(msg);
	}

	public static void debug(String... message) {
		if (!debug) {
			return;
		}
		String[] debugMsg = new String[message.length + 1];
		debugMsg[0] = debugPrefix;
		System.arraycopy(message, 0, debugMsg, 1, message.length);
		println(debugMsg);
	}
}
