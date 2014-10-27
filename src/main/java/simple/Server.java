package simple;

import org.apache.http.util.ByteArrayBuffer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by eugenii.samarskyi on 27.10.2014.
 */
public class Server {
    public static void main(String args[]) throws UnsupportedEncodingException {

        String data = "My Message";
        byte id = 1;

        int contentLength =  data.getBytes().length + 1;


        final byte[] utf8Bytes = data.getBytes("UTF-8");
        System.out.println("Bytes for send: " + utf8Bytes.length);

        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(5000);

        byteArrayBuffer.append(utf8Bytes.length);
//        byteArrayBuffer.append(id);

//        byteArrayBuffer.append(data.getBytes(), 0, data.length());

        ServerSocket srvr = null;
        Socket skt = null;
        DataOutputStream out = null;

        try {
            srvr = new ServerSocket(1234);
            skt = srvr.accept();
            System.out.print("Server has connected!\n");

            out = new DataOutputStream(skt.getOutputStream());
            System.out.print("Sending string: '" + data + "'\n");
            out.write(utf8Bytes.length);
//            out.write(contentLength);
            out.flush();

        }
        catch(Exception e) {
            System.out.print("Whoops! It didn't work!\n");
        }finally {
            try {
                out.close();
                skt.close();
                srvr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
