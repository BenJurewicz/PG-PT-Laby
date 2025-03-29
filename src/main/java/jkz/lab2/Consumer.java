package jkz.lab2;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer implements Runnable {
	private final BlockingQueue<Answer> answers;
	private final AtomicBoolean stopSignal;

	public Consumer(BlockingQueue<Answer> answers, AtomicBoolean stopSignal) {
		this.answers = answers;
		this.stopSignal = stopSignal;
	}

	@Nullable
	public Answer tryTake() {
		try {
			return answers.poll(500, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void handleAnswer(Answer answer) {
		System.out.println(answer);
	}

	@Override
	public void run() {
		while (!stopSignal.get() || !answers.isEmpty()) {
			Answer answer = tryTake();
			if (answer != null) {
				handleAnswer(answer);
			}
		}
	}
}
