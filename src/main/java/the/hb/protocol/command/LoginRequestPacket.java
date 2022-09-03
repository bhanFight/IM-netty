package the.hb.protocol.command;

import lombok.Data;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/3 21:07
 */
@Data
public class LoginRequestPacket extends Packet{

    private Integer userId;
    private String userName;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
