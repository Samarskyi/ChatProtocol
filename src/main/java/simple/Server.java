package simple;

import messages.ChatMessage;
import messages.ProfileMsg;
import org.apache.http.util.ByteArrayBuffer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

/**
 * Created by eugenii.samarskyi on 27.10.2014.
 */
public class Server {
    public static void main(String args[]) throws UnsupportedEncodingException {

        String data = "My Message";
        byte id = 1;

        int contentLength =  data.getBytes().length + 1;
        final byte[] utf8Bytes = data.getBytes("UTF-8");

//        System.out.println("Bytes for send: " + utf8Bytes.length);

        ServerSocket srvr = null;
        Socket skt = null;
        DataOutputStream out = null;

//        ProfileMsg profileMsg = new ProfileMsg("Vasia","MyAvatar","05.08.2014");
        ChatMessage chatMessage = new ChatMessage("Hi Jack", "Bobby", Calendar.getInstance().getTimeInMillis());

        try {
            srvr = new ServerSocket(1234);
            skt = srvr.accept();

            System.out.print("Server has connected!\n");

            out = new DataOutputStream(skt.getOutputStream());
            System.out.println(chatMessage.serialize().length);
            out.writeInt(chatMessage.serialize().length);
            out.write(chatMessage.serialize(), 0, chatMessage.serialize().length);
            out.flush();
        }
        catch(Exception e) {
            System.out.print("Whoops! It didn't work!\n");
            e.printStackTrace();
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
