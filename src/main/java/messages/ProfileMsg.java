package messages;

import org.apache.http.util.ByteArrayBuffer;

import java.nio.ByteBuffer;

/**
 * @author yurii.ostrovskyi
 */
public class ProfileMsg extends Message {

    private String mName;
    private String mAvatar;
    private String mBday;

    public ProfileMsg() {
    }

    public ProfileMsg(String mName, String mAvatar, String mBday) {
        this.mName = mName;
        this.mAvatar = mAvatar;
        this.mBday = mBday;
    }

    @Override
    public byte[] serialize() {
        ByteArrayBuffer bab = new ByteArrayBuffer(1024);
        byte[] nameSize = ByteBuffer.allocate(mName.getBytes().length).array();
        byte[] avatarSize = ByteBuffer.allocate(mAvatar.getBytes().length).array();
        byte[] bDaySize = ByteBuffer.allocate(mBday.getBytes().length).array();
        byte[] idSize = ByteBuffer.allocate(Message.PROFILE_MESSAGE_ID).array();
//        byte[] totalLength = ByteBuffer.allocate(mName.getBytes().length + mAvatar.getBytes().length + mBday.getBytes().length + 16).array();
//        bab.append(totalLength, 0, totalLength.length);
        bab.append(idSize, 0, idSize.length);
        bab.append(nameSize, 0, nameSize.length);
        bab.append(mName.getBytes(), 0, mName.getBytes().length);
        bab.append(avatarSize, 0, nameSize.length);
        bab.append(mAvatar.getBytes(), 0, mAvatar.getBytes().length);
        bab.append(bDaySize, 0, bDaySize.length);
        bab.append(mBday.getBytes(), 0, mBday.getBytes().length);
        return bab.toByteArray();
    }

    @Override
    public String toString() {
        return "ProfileMsg{" +
                "mName='" + mName + '\'' +
                ", mAvatar='" + mAvatar + '\'' +
                ", mBday='" + mBday + '\'' +
                '}';
    }
}
