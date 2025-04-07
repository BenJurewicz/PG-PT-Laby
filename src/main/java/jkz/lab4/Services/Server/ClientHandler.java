package jkz.lab4.Services.Server;

import jkz.lab4.Helpers.Debug;
import jkz.lab4.Packets.Answer;
import jkz.lab4.Packets.Register;
import jkz.lab4.Packets.Unregister;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
	private final Socket clientSocket;

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
	}

	private void handleRegister(Register register) {
		Debug.print(Server.prefix + "Client " + register.ID() + " connected.");
	}

	private void handleUnregister(Unregister unregister) {
		Debug.print(Server.prefix + "Client " + unregister.ID() + " disconnected.");
	}

	private void handleAnswer(Answer answer) {
		Debug.print(Server.prefix + "Client " + answer.SenderID() + " sent: " + answer);
	}

	private void handleData(Object receivedData) {
		if (receivedData instanceof Register) {
			handleRegister((Register) receivedData);
		} else if (receivedData instanceof Unregister) {
			handleUnregister((Unregister) receivedData);
		} else if (receivedData instanceof Answer) {
			handleAnswer((Answer) receivedData);
		}
	}

	private void mainLoop(ObjectInputStream in) throws IOException {
		while (true) {
			try {
				Object receivedData = in.readObject();
				handleData(receivedData);
			} catch (ClassNotFoundException e) {
				Debug.debug(
						Server.prefix + "Error: Received object of unknown class from " + clientSocket.getInetAddress());
				break;
			} catch (IOException e) {
				break;
			}
		}
	}

	@Override
	public void run() {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {

			mainLoop(objectInputStream);

		} catch (IOException e) {
			Debug.debug(
					Server.prefix + "Error handling client " + clientSocket.getInetAddress() + ": " + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
