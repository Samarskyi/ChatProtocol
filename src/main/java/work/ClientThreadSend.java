package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThreadSend extends Thread {

	private Socket socket;

	public ClientThreadSend(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		
		try {
			
			BufferedReader bufferedReaderFromCommandPrompt = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			
			while (true) {
				String readerInput = bufferedReaderFromCommandPrompt.readLine();
				printWriter.println( readerInput);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
