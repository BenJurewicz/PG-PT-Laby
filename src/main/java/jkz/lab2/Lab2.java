package jkz.lab2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Lab2 {
	private static int producerCount = 5;
	private static int consumerCount = 1;

	private static BlockingQueue<Answer> answers;
	private static AtomicBoolean stopSignal;
	private static List<Thread> threads;

	private static void init() {
		answers = new ArrayBlockingQueue<Answer>(100);
		stopSignal = new AtomicBoolean(false);
		threads = new ArrayList<>(10);
	}

	private static void createTasks(Class<? extends Runnable> type, int count) {
		for (int i = 0; i < count; i++) {
			Runnable task = null;

			try {
				task = type.getConstructor(BlockingQueue.class, AtomicBoolean.class).newInstance(answers, stopSignal);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			Thread thread = new Thread(task);
			threads.add(thread);
		}
	}

	private static void createThreads() {
		createTasks(Producer.class, producerCount);
		createTasks(Consumer.class, consumerCount);
	}

	private static void startThreads() {
		for (Thread thread : threads) {
			thread.start();
		}
	}

	private static void joinThreads() {
		for (Thread thread : threads) {
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
		waitForQuitSignal();

		stopThreads();
		joinThreads();
	}

	public static int getProducerCount() {
		return producerCount;
	}

	public static void setProducerCount(int producerCount) {
		Lab2.producerCount = producerCount;
	}

	public static int getConsumerCount() {
		return consumerCount;
	}

	public static void setConsumerCount(int consumerCount) {
		Lab2.consumerCount = consumerCount;
	}
}
