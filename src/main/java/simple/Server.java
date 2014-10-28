package simple;

import messages.ChatMessage;
import org.apache.http.util.ByteArrayBuffer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;
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

        System.out.println("Bytes for send: " + utf8Bytes.length);

        ServerSocket srvr = null;
        Socket socket = null;
//        ProfileMsg profileMsg = new ProfileMsg("Vasia","MyAvatar","05.08.2014");

        ChatMessage chatMessage = new ChatMessage("Hi Jack", "Bobby", Calendar.getInstance().getTimeInMillis());
		ChatMessage chatMessage1 = new ChatMessage("Hi Sam bla bla bla ", "Sally FUUUUUUUUUUUUUUUUUUUUCKKKKKKKKKKKK", Calendar.getInstance().getTimeInMillis());
		ChatMessage chatMessage2 = new ChatMessage("221Hi Sam bla bla bla ", "Sally FUUUUUUUUUUUUUUUUUUUUCKKKKKKKKKKKK", Calendar.getInstance().getTimeInMillis());
		ChatMessage chatMessage3 = new ChatMessage("345Hi Sam bla bla bla ", "Sally FUUUUUUUUUUUUUUUUUUUUCKKKKKKKKKKKK", Calendar.getInstance().getTimeInMillis());

		ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(1024);
		byteArrayBuffer.append(chatMessage.serialize(), 0, chatMessage.serialize().length);
		byteArrayBuffer.append(chatMessage1.serialize(), 0, chatMessage1.serialize().length);
		byteArrayBuffer.append(chatMessage2.serialize(), 0, chatMessage2.serialize().length);
		byteArrayBuffer.append(chatMessage3.serialize(), 0, chatMessage3.serialize().length);
		byte[] bytes1 = Arrays.copyOfRange(byteArrayBuffer.toByteArray(), 0, 200);
		byte[] bytes2 = Arrays.copyOfRange(byteArrayBuffer.toByteArray(), 200, byteArrayBuffer.toByteArray().length);

		try {
            srvr = new ServerSocket(1234);
            socket = srvr.accept();
			OutputStream outputStream = socket.getOutputStream();

            System.out.print("Server has connected!\n");
            outputStream.write(bytes1);
			Thread.sleep(2000);
			outputStream.write(bytes2);
        }
        catch(Exception e) {
            System.out.print("Whoops! It didn't work!\n");
            e.printStackTrace();
        }finally {
            try {
                socket.close();
                srvr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
