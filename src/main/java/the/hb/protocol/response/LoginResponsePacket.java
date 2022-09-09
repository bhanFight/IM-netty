package the.hb.protocol.response;

import lombok.Data;
import the.hb.protocol.Packet;
import the.hb.protocol.command.Command;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/4 18:58
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;
    private String userId;
    private String reason;

    public boolean isSuccess(){
        return success;
    }
    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
