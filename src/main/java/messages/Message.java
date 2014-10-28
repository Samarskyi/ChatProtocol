package messages;

import java.nio.ByteBuffer;

/**
 * @author yurii.ostrovskyi
 */
public abstract class Message {
    public static final int CHAT_MESSAGE_ID = 1;
    public static final int PROFILE_MESSAGE_ID = 2;

    public abstract byte[] serialize();

    public abstract int getId();

    public static Message deserialize(byte[] bytes) {
        int id = ByteBuffer.wrap(bytes, 0, 4).getInt();
        switch (id) {
            case CHAT_MESSAGE_ID:
                return new ChatMessage(bytes);

            case PROFILE_MESSAGE_ID:
                return new ProfileMessage(bytes);
        }
        //TODO maybe i should return null message
        return null;
    }
}
