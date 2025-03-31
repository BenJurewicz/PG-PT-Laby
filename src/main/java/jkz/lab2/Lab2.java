package jkz.lab2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Lab2 {
	private static final int producerCount = 5;
	private static final int consumerCount = 1;

	private static BlockingQueue<Answer> answers;
	private static AtomicBoolean stopSignal;

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

	private static void init() {
		answers = new ArrayBlockingQueue<Answer>(100);
		stopSignal = new AtomicBoolean(false);
		producers = new ArrayList<>(producerCount);
		consumers = new ArrayList<>(consumerCount);

		NumberGenerator.init((Long.MAX_VALUE / 10) - 20, (Long.MAX_VALUE / 10) - 1, stopSignal);
	}

	private static void createTasks(Class<? extends Runnable> type, List<Thread> threadList, int count) {
		for (int i = 0; i < count; i++) {
			Runnable task = null;
			try {
				task = type.getConstructor(BlockingQueue.class, AtomicBoolean.class).newInstance(answers, stopSignal);
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
			System.in.read();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		init();
		createThreads();
		startThreads();
		//		waitForQuitSignal();
		//		stopThreads(); // NumberGenerator stops threads currently
		joinThreads();
	}
}
