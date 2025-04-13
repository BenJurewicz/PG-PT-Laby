package jkz;

import jkz.Services.Client.ClientGenerator;
import jkz.Services.Server.Server;

import java.util.List;

public class Lab4 {
	public static void main(String[] args) {
		String serverAddress = "localhost";
		int port = 12345;
		int numberOfClients = 5;
		long min = (Long.MAX_VALUE / 10) - 10;
		long max = (Long.MAX_VALUE / 10) - 1;

		Thread serverThread = new Thread(new Server(port, numberOfClients, 10_000));
		serverThread.start();

		List<Thread> threads = ClientGenerator.generate(numberOfClients, port, serverAddress, min, max);
		threads.add(serverThread);

		joinThreads(threads);
	}

	private static void joinThreads(List<Thread> threads) {
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
