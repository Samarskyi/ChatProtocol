package work;

import org.apache.http.util.ByteArrayBuffer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MyServer {

	private static Vector<MyTClient> clientsList;
	private ServerSocket serverSocket;
    ByteArrayBuffer b;
	public static void main(String[] args) throws IOException {

		new MyServer();

	}

	public MyServer() throws IOException {

		serverSocket  = new ServerSocket(6000);
		clientsList   = new Vector<MyTClient>();

		int i = 0;
		while (true) {

			Socket    socket   = serverSocket.accept();
			MyTClient myClient = new MyTClient(++i);
			System.out.println("Connected"+ socket);
			
			myClient.setSocket(socket);
			clientsList.add(myClient);
			myClient.start();

		}
	}

	public static synchronized void sendMessToAllClients(String message) throws IOException {

		for (int i = 0; i < clientsList.size(); i++) {

			MyTClient client = clientsList.get(i);
			client.sendMessage(message);
//			client.dataOutputStream.writeUTF(m);
//			client.dataOutputStream.flush();

		}
	}

}
