package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetServer implements AutoCloseable {
	int portNumber;
	private final ServerSocket socketServer;

	public NetServer(int portNumber) throws IOException {
		this.portNumber = portNumber;
		socketServer = new ServerSocket(this.portNumber);
	}

	public void runServer() throws IOException {
		while (true) {
			try {
				System.out.println("Waiting for incoming connections");
				Socket socket = socketServer.accept();
				ServerThread newThread = new ServerThread(socket);
		        newThread.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public void close() throws Exception {
		if (socketServer != null) {
			socketServer.close();
		}
	}	
}
	