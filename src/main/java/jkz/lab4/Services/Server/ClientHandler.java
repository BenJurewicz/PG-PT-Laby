package jkz.lab4.Services.Server;

import jkz.lab4.Helpers.Debug;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
	private final Socket clientSocket;

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
	}

	private void handleData(Object receivedData) {
		System.out.println("Received: " + receivedData);
	}

	private void mainLoop(ObjectInputStream in) throws IOException {
		while (true) {
			try {
				Object receivedData = in.readObject();
				handleData(receivedData);
			} catch (ClassNotFoundException e) {
				Debug.print("Server: Error: Received object of unknown class from " + clientSocket.getInetAddress());
				break;
			} catch (IOException e) {
				Debug.print("Server: Client " + clientSocket.getInetAddress() + " disconnected.");
				break;
			}
		}
		Debug.print("Server: Client " + clientSocket.getInetAddress() + " finished sending data.");
	}

	@Override
	public void run() {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {

			mainLoop(objectInputStream);

		} catch (IOException e) {
			Debug.print("Server: Error handling client " + clientSocket.getInetAddress() + ": " + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

