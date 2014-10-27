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

                int nameSizeOff = 4;
                int textOff = 8;
                int nameOff = 12;

                int textLength = ByteBuffer.wrap(bytes, nameSizeOff, 4).getInt();
                String text = new String(Arrays.copyOfRange(bytes, textOff, textOff + textLength));
                int nameLength = ByteBuffer.wrap(bytes, textOff + textLength, 4).getInt();
                String name = new String(Arrays.copyOfRange(bytes, nameOff + textLength, nameOff + textLength + nameLength));
                long timeInMillis = ByteBuffer.wrap(bytes, nameOff + textLength + nameLength, 8).getLong();

                return new ChatMessage(text, name, timeInMillis);

            case PROFILE_MESSAGE_ID:

                int profileNameSizeOff = 4;
                int profileNameOff = 8;
                int avatarOff = 12;
                int bDayOff = 16;

                int profileNameLength = ByteBuffer.wrap(bytes, profileNameSizeOff, 4).getInt();
                String profileName = new String(Arrays.copyOfRange(bytes, profileNameOff, profileNameOff + profileNameLength));
                int avatarLength = ByteBuffer.wrap(bytes, profileNameOff + profileNameLength, 4).getInt();
                String avatar = new String(Arrays.copyOfRange(bytes, avatarOff + profileNameLength, avatarOff + profileNameLength + avatarLength));
                int bDayLength = ByteBuffer.wrap(bytes, avatarOff + profileNameLength + avatarLength, 4).getInt();
                String bDay = new String(Arrays.copyOfRange(bytes, bDayOff + profileNameLength + avatarLength, bDayOff + profileNameLength + avatarLength + bDayLength));

                return new ProfileMsg(profileName, avatar, bDay);
        }
        //TODO maybe i should return null message
        return null;
    }
}
