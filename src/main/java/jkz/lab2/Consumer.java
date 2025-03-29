package jkz.lab2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer implements Runnable {
	private final BlockingQueue<Answer> answers;
	private final AtomicBoolean stopSignal;

	public Consumer(BlockingQueue<Answer> answers, AtomicBoolean stopSignal) {
		this.answers = answers;
		this.stopSignal = stopSignal;
	}

	public Answer tryTake() {
		try {
			return answers.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void handleAnswer(Answer answer) {
		System.out.println(answer);
	}

	@Override
	public void run() {
		while (!stopSignal.get()) {
			Answer answer = tryTake();
			handleAnswer(answer);
		}
	}
}
