package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThreadReceive extends Thread {

	private Socket socket;

	public ClientThreadReceive(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) {
				System.out.println(input.readLine());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
