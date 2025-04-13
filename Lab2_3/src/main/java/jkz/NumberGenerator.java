package jkz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class NumberGenerator {
	private static long min = 1, max = 100;
	private static final List<Long> numbers = new ArrayList<Long>();
	private static AtomicBoolean stopSignal = null;

	public static void init(long min, long max, AtomicBoolean stopSignal) {
		NumberGenerator.stopSignal = stopSignal;
		NumberGenerator.min = min;
		NumberGenerator.max = max;
		generateList();
	}

	private static void generateList() {
		for (long i = min; i <= max; i++) {
			numbers.add(i);
		}
		Collections.shuffle(numbers);
	}

	public static boolean isEmpty() {
		return numbers.isEmpty();
	}

	public static synchronized Optional<Long> generateNumber() {
		if (numbers.isEmpty()) {
			stopSignal.set(true);
			return Optional.empty();
		}
		return Optional.of(numbers.remove(numbers.size() - 1));
	}
}
