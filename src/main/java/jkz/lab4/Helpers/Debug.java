package jkz.lab4.Helpers;

public class Debug {
	private static final boolean debug = false;

	public static void print(String message) {
		if (debug) {
			System.out.println(message);
		}
	}
}
