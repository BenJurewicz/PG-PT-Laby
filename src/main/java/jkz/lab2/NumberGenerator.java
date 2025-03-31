package jkz.lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberGenerator {
    private static long min = 1, max = 100;
    private static final List<Long> numbers = new ArrayList<Long>();

    public static void setMinMax(long min, long max) {
        NumberGenerator.min = min;
        NumberGenerator.max = max;
    }

    private static void generateList() {
        for (long i = min; i <= max; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
    }

    public static synchronized long generateNumber() {
        if (numbers.isEmpty()) {
            generateList();
        }
        return numbers.remove(numbers.size() - 1);
    }
}
