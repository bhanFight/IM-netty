package the.hb.protocol.response;

import lombok.Data;
import the.hb.Session.Session;
import the.hb.protocol.Packet;
import the.hb.protocol.command.Command;

import java.util.List;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/7 23:26
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    private String groupId;
    private List<String> groupMembers;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
