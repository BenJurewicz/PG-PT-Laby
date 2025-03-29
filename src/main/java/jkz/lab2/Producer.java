package jkz.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Producer implements Runnable {
	private final BlockingQueue<Answer> answers;
	private final AtomicBoolean stopSignal;

	public Producer(BlockingQueue<Answer> answers, AtomicBoolean stopSignal) {
		this.answers = answers;
		this.stopSignal = stopSignal;
	}

	private int getNumber() {
		return NumberGenerator.generateNumber();
	}

	private List<Integer> getDivisors() {
		List<Integer> divisors = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			divisors.add(NumberGenerator.generateNumber());
		}
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
		while (!stopSignal.get()) {
			int mockNumber = getNumber();
			List<Integer> mockDivisors = getDivisors();
			Answer answer = new Answer(mockNumber, mockDivisors);

			// TODO: Remove this
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			tryPut(answer);
		}
	}
}
