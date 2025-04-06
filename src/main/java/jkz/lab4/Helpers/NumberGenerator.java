package jkz.lab4.Helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class NumberGenerator {
	private final long min, max;
	private final List<Long> numbers = new ArrayList<>();

	public NumberGenerator(long min, long max) {
		this.min = min;
		this.max = max;
		generateList();
	}

	private void generateList() {
		for (long i = min; i <= max; i++) {
			numbers.add(i);
		}
		Collections.shuffle(numbers);
	}

	public boolean isEmpty() {
		return numbers.isEmpty();
	}

	public Optional<Long> generateNumber() {
		if (numbers.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(numbers.remove(numbers.size() - 1));
	}
}
