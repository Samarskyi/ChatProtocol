package work;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyTClient extends Thread {

    private DataOutputStream dataOutputStream;
    private OutputStream outputStream;

    private InputStream inputStream;
    private DataInputStream dataInputStream;

    private String str = null;
    private Socket socket = null;
    private int i = 0;

    public MyTClient(int num) {
        i = num;
    }

    @Override
    public void run() {

        try {
            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);

            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);

            while (true) {

                str = dataInputStream.readUTF();

                MyServer.sendMessToAllClients(i + ": " + str);

                if (str.equals("exit")) {
                    dataOutputStream.writeUTF("EXIT");
                    socket.close();
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Error");

        } finally {
            try {
                System.out.println("Client left: " + i);
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void setSocket(Socket s) {
        this.socket = s;
    }

    public void sendMessage(String str) throws IOException {
        //todo NEED crate protocol
        dataOutputStream.writeUTF(str);
        dataOutputStream.flush();
    }

}
