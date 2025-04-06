package jkz.lab2_3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Consumer implements Runnable {
	static AtomicLong COUNT = new AtomicLong(0);
	private final BlockingQueue<Answer> answers;
	private final AtomicBoolean stopSignal;
	private final AtomicBoolean forceStopSignal;

	public Consumer(BlockingQueue<Answer> answers, AtomicBoolean stopSignal, AtomicBoolean forceStopSignal) {
		this.answers = answers;
		this.stopSignal = stopSignal;
		this.forceStopSignal = forceStopSignal;
	}

	public Answer tryTake() {
		try {
			return answers.poll(500, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void handleAnswer(Answer answer) {
		System.out.print(COUNT.incrementAndGet() + ":  ");
		System.out.println(answer);
	}

	@Override
	public void run() {
		while (!forceStopSignal.get() && (!stopSignal.get() || !answers.isEmpty() || Lab2_3.areProducersRunning())) {
			Answer answer = tryTake();
			if (answer != null) {
				handleAnswer(answer);
			}
		}
	}
}
