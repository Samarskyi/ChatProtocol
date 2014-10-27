package work;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class MyClient {

	private String           str = null;
	private Socket           socket;
	private OutputStream     outputStream;
	private DataOutputStream dataOutStream;
	private BufferedReader   buffReader;

	public static void main(String[] args) throws IOException, InterruptedException {

		new MyClient();

	}

	public MyClient() throws IOException, InterruptedException {

		socket = new Socket("127.0.0.1", 6000);

		outputStream = socket.getOutputStream();
		dataOutStream = new DataOutputStream(outputStream);

		buffReader = new BufferedReader(new InputStreamReader(System.in, "Cp1251"));

		
		GiveMeFuckingMessage messageListener = new GiveMeFuckingMessage();
		messageListener.setDaemon(true);
		messageListener.start();

		while (true) {

			str = buffReader.readLine();
			dataOutStream.writeUTF(str);
			dataOutStream.flush();
		}
	}

	
//  get messages from other clients using server
	class GiveMeFuckingMessage extends Thread {

		DataInputStream dataInputStream;
		InputStream     inputStream;

		
		public GiveMeFuckingMessage() throws IOException, InterruptedException {

			inputStream     = socket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			Thread.sleep(1000);
		}

		
		@Override
		public void run() {

			try {
				
				String ss = null;
				while (true) {

					ss = dataInputStream.readUTF();

					if (ss.equals("EXIT")) {
						dataInputStream.close();
						socket.close();
					}

					System.out.println(ss);
				}
				
			} catch (IOException e) {
				System.err.println("Connection error");
			
			} finally {
				try {

					socket.close();

				} catch (IOException e) {
					System.err.println("Socket not closed");
				}
			}
		}

	}
}

