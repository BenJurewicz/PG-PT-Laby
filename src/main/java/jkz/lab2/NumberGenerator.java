package jkz.lab2;

public class NumberGenerator {
    private static long min = 1, max = 100;

    public static void setMinMax(long min, long max) {
        NumberGenerator.min = min;
        NumberGenerator.max = max;
    }

    public static synchronized long generateNumber() {
        return (long) Math.round(Math.random() * (max - min) + min);
    }
}
