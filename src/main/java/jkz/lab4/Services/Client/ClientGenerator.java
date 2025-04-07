package jkz.lab4.Services.Client;

import jkz.lab4.Helpers.Debug;

import java.util.ArrayList;
import java.util.List;

public class ClientGenerator {
	public static void main(String[] args) {
		String serverAddress = "localhost";
		int port = 12345;

		int numberOfClients = 5;

		long min = (Long.MAX_VALUE / 10) - 20;
		long max = (Long.MAX_VALUE / 10) - 1;

		List<Thread> threads = generate(numberOfClients, port, serverAddress, min, max);

		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<Thread> generate(int numberOfClients, int port, String serverAddress, long min, long max) {
		ArrayList<Thread> threads = new ArrayList<>();
		long step = Math.ceilDiv(max - min, numberOfClients);

		for (int i = 1; i <= numberOfClients; i++) {
			long clientMin = min + (i - 1) * step;
			long clientMax = Math.min(min + i * step - 1, max);

			Client client = new Client(serverAddress, port, clientMin, clientMax);
			Thread clientThread = new Thread(client);
			clientThread.start();

			threads.add(clientThread);
		}
		Debug.debug(Client.prefix + "Launched " + numberOfClients + " clients.");

		return threads;
	}
}