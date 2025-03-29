package jkz.lab2;

public class NumberGenerator {
	private static int min = 1, max = 100;

	public static void setMinMax(int min, int max) {
		NumberGenerator.min = min;
		NumberGenerator.max = max;
	}

	public static synchronized int generateNumber() {
		return (int) Math.round(Math.random() * (max - min) + min);
	}
}
