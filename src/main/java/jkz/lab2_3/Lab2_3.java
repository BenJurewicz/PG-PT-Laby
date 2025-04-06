package jkz.lab2_3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Lab2_3 {
	private static int producerCount = 5;
	private static final int consumerCount = 1;

	private static BlockingQueue<Answer> answers;
	private static AtomicBoolean stopSignal;
	public static AtomicBoolean forceStopSignal;

	private static List<Thread> producers;
	private static List<Thread> consumers;

	public static boolean areProducersRunning() {
		for (Thread p : producers) {
			if (p.isAlive()) {
				return true;
			}
		}
		return false;
	}

	private static void init(String[] args) {
		answers = new ArrayBlockingQueue<Answer>(100);
		stopSignal = new AtomicBoolean(false);
		forceStopSignal = new AtomicBoolean(false);
		producers = new ArrayList<>(producerCount);
		consumers = new ArrayList<>(consumerCount);

		NumberGenerator.init((Long.MAX_VALUE / 10) - 20, (Long.MAX_VALUE / 10) - 1, stopSignal);

		if (args.length == 0) {
			System.out.println("No arguments provided");
			return;
		}
		int pCount;
		try {
			pCount = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			pCount = producerCount;
		}
		if (pCount < 2) {
			System.out.println("Minimum number of producers is 2, defaulting to 2");
			pCount = 2;
		}
		producerCount = pCount - 1;
	}

	private static void createTasks(Class<? extends Runnable> type, List<Thread> threadList, int count) {
		for (int i = 0; i < count; i++) {
			Runnable task = null;
			try {
				task = type.getConstructor(BlockingQueue.class, AtomicBoolean.class, AtomicBoolean.class).newInstance(
						answers, stopSignal, forceStopSignal);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			Thread thread = new Thread(task, type.getSimpleName() + " #" + i);
			threadList.add(thread);
		}
	}

	private static void createThreads() {
		createTasks(Producer.class, producers, producerCount);
		createTasks(Consumer.class, consumers, consumerCount);
	}

	private static void startThreads() {
		for (Thread thread : producers) {
			thread.start();
		}
		for (Thread thread : consumers) {
			thread.start();
		}
	}

	private static void joinThreads() {
		for (Thread thread : producers) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		// We have to stop consumers after producers
		// to make sure all the answers have benn consumed.
		// Otherwise, a producer might get stuck while waiting
		// to put an answer in a full queue.
		for (Thread thread : consumers) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static void stopThreads() {
		stopSignal.set(true);
	}

	private static void waitForQuitSignal() {
		try {
			while (System.in.available() == 0 && !stopSignal.get()) {
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		forceStopSignal.set(true);
	}

	public static void main(String[] args) {
		init(args);
		createThreads();
		startThreads();
		waitForQuitSignal();
		stopThreads(); // NumberGenerator stops threads currently
		joinThreads();
	}
}
