package simple;

import messages.ChatMessage;
import messages.ProfileMsg;
import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by eugenii.samarskyi on 27.10.2014.
 */
public class Client {
    public static void main(String args[]) {
        DataInputStream in = null;

        try {

            Socket skt = new Socket("localhost", 1234);
            in = new DataInputStream(skt.getInputStream());
            System.out.print("Received size: ");

            int size = in.readInt();
            ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(size);
            if(size > 0){

                System.out.println(size); // Read one line and output it
                int current;
                while ((current = in.read()) != -1) {
                    byteArrayBuffer.append((byte)current);
                }
                String str = new String(byteArrayBuffer.toByteArray(), "UTF-8");
                ProfileMsg profileMsg = (ProfileMsg) ChatMessage.deserialize(byteArrayBuffer.toByteArray());
                System.out.println(profileMsg);
            }

        } catch (Exception e) {
            System.out.print("Whoops! It didn't work!\n");
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
