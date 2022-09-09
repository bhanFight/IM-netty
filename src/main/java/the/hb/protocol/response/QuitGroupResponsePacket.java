package the.hb.protocol.response;

import lombok.Data;
import the.hb.protocol.Packet;
import the.hb.protocol.command.Command;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/8 14:29
 */
@Data
public class QuitGroupResponsePacket extends Packet {

    private boolean success;
    private String message;

    public boolean isSuccess(){
        return success;
    }

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}
