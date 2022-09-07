package the.hb.protocol.request;

import the.hb.protocol.Packet;
import the.hb.protocol.command.Command;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/7 18:04
 */
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
