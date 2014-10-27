package simple;

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

            ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(5000);

            Socket skt = new Socket("localhost", 1234);
            in = new DataInputStream(skt.getInputStream());
            System.out.print("Received string: ");
            System.out.println(in.readInt()); // Read one line and output it
            System.out.print("'\n");

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
