package jkz.Services.Server;

import jkz.Helpers.Colors;
import jkz.Helpers.Debug;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
	public static final String prefix = Colors.CYAN + "Server: " + Colors.RESET;

	private final int timeout;
	private final int port;
	private final ExecutorService clientProcessingPool;

	public Server(int port, int maxClients, int timeout) {
		this.port = port;
		this.clientProcessingPool = Executors.newFixedThreadPool(maxClients);
		this.timeout = timeout;
	}

	private void mainLoop(ServerSocket serverSocket) throws IOException {
		while (true) {
			Socket clientSocket = serverSocket.accept();
			clientProcessingPool.submit(new ClientHandler(clientSocket));
		}
	}

	@Override
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			serverSocket.setSoTimeout(timeout);
			Debug.debug(prefix, "Server started on port " + port);
			mainLoop(serverSocket);
		} catch (SocketTimeoutException e) {
			Debug.debug(prefix, "ServerSocket timed out.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (clientProcessingPool != null) {
				clientProcessingPool.shutdown();
			}
		}
	}


	public static void main(String[] args) {
		int port = 12345;
		int maxClients = 10;

		Server server = new Server(port, maxClients, 10_000);
		server.run();
	}
}