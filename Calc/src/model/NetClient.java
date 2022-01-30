package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import controller.MainWindowController;

public class NetClient implements AutoCloseable {
	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private static String response;

	public NetClient(String serverAddress, int portNumber) throws UnknownHostException, IOException {
		socket = new Socket(serverAddress, portNumber);
		inputStream = socket.getInputStream();
	}

	public void send() throws IOException {
		outputStream = socket.getOutputStream();
		boolean isAutoFlush = true;
		PrintWriter send = new PrintWriter(outputStream, isAutoFlush);
		String expression = MainWindowController.getExpression();
		send.printf("%s\n", expression);
		
		response = "";
		try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			response = bufferedReader.readLine();
			if (response.length() > 0) {
				double r1 = (int) (Double.parseDouble(response));
				double r2 = Double.parseDouble(response);
				if (r1 == r2) {
					int r = (int) (Double.parseDouble(response));
					response = ("" + r);
//					System.out.println("r1= r2" + ", r= " + r + ", result= " + response);
				} else {
//					System.out.println("r1 != r2");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(expression + "=" + response);
	}

	public static String getResponse() {
		return response;
	}

	@Override
	public void close() throws Exception {
		if (socket != null)
			socket.close();
	}
}