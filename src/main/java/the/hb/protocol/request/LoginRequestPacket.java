package the.hb.protocol.request;


import lombok.Data;
import the.hb.protocol.command.Command;
import the.hb.protocol.Packet;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/3 21:07
 */
@Data
public class LoginRequestPacket extends Packet {

    private String userId;
    private String userName;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

}
