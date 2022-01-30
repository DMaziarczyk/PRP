package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {

	private InputStream inputStream;
	private OutputStream outputStream;
	private String result;
	private String expression;
	private static int portNumber = 9001;
	Socket socket;

	ServerThread(Socket socket) {
		this.socket = socket;
	}	

	@Override
	public void run() {
		try {
			System.out.println("New thread");
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			expression = "";
			result = "";
			boolean isAutoFlush = true;
			try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					PrintWriter response = new PrintWriter(outputStream, isAutoFlush);) {
				expression = bufferedReader.readLine();
				result = Calc.calculate(expression);
				response.println(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
public static void main(String[] args) throws Exception {
		
		NetServer server = new NetServer(portNumber);
		System.out.println("Server is running");
		server.runServer();
		server.close();
	}
}