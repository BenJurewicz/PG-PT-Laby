package jkz.lab4.Services.Client;

import jkz.lab4.Helpers.Debug;
import jkz.lab4.Helpers.NumberGenerator;
import jkz.lab4.Packets.Answer;
import jkz.lab4.Packets.Register;
import jkz.lab4.Packets.Unregister;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Client implements Runnable {
	static AtomicInteger clientIdCounter = new AtomicInteger(0);

	private final NumberGenerator numberGenerator;
	private final String serverAddress;
	private final int port;
	private final int clientId;

	public Client(String serverAddress, int port, long min, long max) {
		this.serverAddress = serverAddress;
		this.port = port;
		this.clientId = clientIdCounter.incrementAndGet();
		this.numberGenerator = new NumberGenerator(min, max);
	}

	private Optional<Answer> generateAnswer() {
		Optional<Long> number = numberGenerator.generateNumber();
		if (number.isEmpty()) {
			return Optional.empty();
		}
		List<Long> divisors = new ArrayList<>();
		return Optional.of(new Answer(clientId, number.get(), divisors));
	}

	private void mainLoop(ObjectOutputStream out) throws IOException, InterruptedException {
		while (true) {
			Optional<Answer> answer = generateAnswer();
			if (answer.isEmpty()) {
				break;
			}
			out.writeObject(answer.get());
			out.flush();
			Debug.print("Client: Client " + clientId + " sent: " + answer.get());

			Thread.sleep(3000); // TODO: Remove after generating the answer correctly
		}
	}

	private void register(ObjectOutputStream stream) throws IOException {
		Register register = new Register(clientId);
		stream.writeObject(register);
		stream.flush();
	}

	private void unregister(ObjectOutputStream stream) throws IOException {
		Unregister unregister = new Unregister(clientId);
		stream.writeObject(unregister);
		stream.flush();
	}

	@Override
	public void run() {
		try (Socket socket = new Socket(serverAddress, port);
		     ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {

			Debug.print("Client: Client " + clientId + " connected to server at " + serverAddress + ":" + port);
			register(objectOutputStream);
			mainLoop(objectOutputStream);
			unregister(objectOutputStream);
			Debug.print("Client: Client " + clientId + " finished sending data.");

		} catch (Exception e) {
			Debug.print("Client: Client " + clientId + " error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String serverAddress = "localhost";
		int port = 12345;
		Client client = new Client(serverAddress, port, 1, 100);
		client.run();
	}
}