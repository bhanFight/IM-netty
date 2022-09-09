package the.hb.protocol.response;

import lombok.Data;
import the.hb.protocol.Packet;
import the.hb.protocol.command.Command;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/8 19:23
 */
@Data
public class MessageGroupResponsePacket extends Packet {

    private boolean success;
    private String fromUser;
    private String message;
    private String wrongMessage;

    public boolean isSuccess(){
        return success;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_GROUP_RESPONSE;
    }
}
