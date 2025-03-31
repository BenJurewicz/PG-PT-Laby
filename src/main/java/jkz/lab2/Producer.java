package jkz.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Producer implements Runnable {
	private final BlockingQueue<Answer> answers;
	private final AtomicBoolean stopSignal;

	public Producer(BlockingQueue<Answer> answers, AtomicBoolean stopSignal) {
		this.answers = answers;
		this.stopSignal = stopSignal;
	}

	private Optional<Long> getNumber() {
		return NumberGenerator.generateNumber();
	}

	private List<Long> getDivisors(long number) {
		List<Long> divisors = new ArrayList<>();

		// 0 has infinite divisors
		if (number == 0L) {
			return divisors;
		}

		for (long i = 1; i <= Math.floor(Math.sqrt(number)); i++) {
			if (number % i == 0) {
				divisors.add(i);
				if (i != number / i) {
					divisors.add(number / i); // add the complementary divisor
				}
			}
		}

		divisors.sort(null);

		return divisors;
	}

	private void tryPut(Answer answer) {
		try {
			answers.put(answer);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void run() {
		while (!stopSignal.get() || !answers.isEmpty()) {
			Optional<Long> generatedNumber = getNumber();
			if (!generatedNumber.isPresent()) {
				break;
			}
			long generatedNumberValue = generatedNumber.get();
			List<Long> divisors = getDivisors(generatedNumberValue);
			Answer answer = new Answer(generatedNumberValue, divisors);
			tryPut(answer);
		}
	}
}
