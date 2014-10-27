package messages;

import org.apache.http.util.ByteArrayBuffer;

import java.nio.ByteBuffer;
import java.sql.Timestamp;

/**
 * @author yurii.ostrovskyi
 */
public class ChatMessage extends Message{

    private String mText;
    private String mName;
    private Timestamp mMessageTime;

    public ChatMessage(String text, String name, long timeInMillis) {
        mText = text;
        mName = name;
        mMessageTime = new Timestamp(timeInMillis);
    }

    public String getText() {
        return mText;
    }

    @Override
    public byte[] serialize() {
        ByteArrayBuffer bab = new ByteArrayBuffer(1024);

        byte[] idSize = ByteBuffer.allocate(4).putInt(Message.CHAT_MESSAGE_ID).array();
        byte[] textSize = ByteBuffer.allocate(4).putInt(mText.getBytes().length).array();
        byte[] nameSize = ByteBuffer.allocate(4).putInt(mName.getBytes().length).array();
        byte[] currentTime = ByteBuffer.allocate(8).putLong(mMessageTime.getTime()).array();

        bab.append(idSize, 0, idSize.length);
        bab.append(textSize, 0, textSize.length);
        bab.append(mText.getBytes(), 0, mText.getBytes().length);
        bab.append(nameSize, 0, nameSize.length);
        bab.append(mName.getBytes(), 0, mName.getBytes().length);
        bab.append(currentTime, 0, currentTime.length);

        return bab.toByteArray();
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "mText='" + mText + '\'' +
                ", mName='" + mName + '\'' +
                ", mMessageTime=" + mMessageTime +
                '}';
    }
}
