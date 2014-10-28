package simple;

import messages.Message;
import org.apache.http.util.ByteArrayBuffer;

import java.io.DataInputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by eugenii.samarskyi on 27.10.2014.
 */
public class Client {
    public static void main(String args[]) {
        DataInputStream in = null;

        try {

            Socket socket = new Socket("localhost", 1234);
            System.out.print("Received size: ");


			int position = 0;
			int messageLength = 0;
			ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(4096);
			while (!Thread.interrupted()) {
				byte[] buffer = new byte[100];
				int bytesRead = socket.getInputStream().read(buffer);
				if (bytesRead > 0) {
					System.out.println("Read some more data from socket");
					byteArrayBuffer.append(buffer, 0, bytesRead);
					System.out.println("bytes received: " + bytesRead);
					System.out.println("byteArrayBuffer.length() = " + byteArrayBuffer.length());
					while (byteArrayBuffer.length() >= position + messageLength) {
						if (byteArrayBuffer.length() >= position + 4) {
							messageLength = ByteBuffer.wrap(byteArrayBuffer.toByteArray(), position, 4).getInt();
						}
						if (byteArrayBuffer.length() >= position + messageLength) {
							System.out.println("position = "+position);
							System.out.println("Message Length = "+messageLength);
							System.out.println(Message.deserialize(Arrays.copyOfRange(byteArrayBuffer.toByteArray(), position, position + messageLength)));
							position += messageLength;
							messageLength = 0;
						}
					}
				}
			}
//				bytesInByteBuffer += bytesRead;

//
//
//					messageLength = byteBuffer.getInt(position);
//					if (bytesInByteBuffer >= position + intByteLength + messageLength) {
//						System.out.println(Message.deserialize(byteBuffer.get(byteBuffer.array()
//								, intByteLength, messageLength).array()));
//						position = position + intByteLength + messageLength;
//						if (bytesInByteBuffer == position + intByteLength + messageLength) {
//							byteBuffer.clear();
//							position = 0;
//							bytesInByteBuffer = 0;
//						}
//					} else {
//						break;
//					}
//				}
        } catch (Exception e) {
            System.out.print("Whoops! It didn't work!\n");
            e.printStackTrace();
        }
    }
}
