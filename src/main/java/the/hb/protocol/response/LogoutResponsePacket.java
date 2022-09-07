package the.hb.protocol.response;

import lombok.Data;
import the.hb.protocol.Packet;
import the.hb.protocol.command.Command;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/7 18:11
 */
@Data
public class LogoutResponsePacket extends Packet {
    private boolean success;
    private String message;
    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}
