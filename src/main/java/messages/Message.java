package messages;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author yurii.ostrovskyi
 */
public abstract class Message {
    public static final int CHAT_MESSAGE_ID = 1;
    public static final int PROFILE_MESSAGE_ID = 2;

    public abstract byte[] serialize();

    public static Message deserialize(byte[] bytes) {
        int id = ByteBuffer.wrap(bytes, 0, 4).getInt();
        switch (id) {
            case CHAT_MESSAGE_ID:
                int textLength = ByteBuffer.wrap(bytes, 4, 4).getInt();
                String text = new String(Arrays.copyOfRange(bytes, 7, textLength));
                int nameLength = ByteBuffer.wrap(bytes, 7 + textLength, 4).getInt();
                String name = new String(Arrays.copyOfRange(bytes, 11 + textLength, nameLength));
                long timeInMillis = ByteBuffer.wrap(bytes, 11 + textLength + nameLength, 8).getLong();

                return new ChatMessage(text, name, timeInMillis);

            case PROFILE_MESSAGE_ID:
                int profileNameLength = ByteBuffer.wrap(bytes, 4, 4).getInt();
                String profileName = new String(Arrays.copyOfRange(bytes, 8, 8 + profileNameLength));
                int avatarLength = ByteBuffer.wrap(bytes, 9 + profileNameLength, 4).getInt();
                String avatar = new String(Arrays.copyOfRange(bytes, 12 + profileNameLength, 12 + profileNameLength + avatarLength));
//                int bDayLength = ByteBuffer.wrap(bytes, 11 + profileNameLength + avatarLength, 4).getInt();
//                String bDay = new String(Arrays.copyOfRange(bytes, 14 + profileNameLength + avatarLength, bDayLength));

                return new ProfileMsg(profileName, avatar, null);
        }
        //TODO maybe i should return null message
        return null;
    }
}
