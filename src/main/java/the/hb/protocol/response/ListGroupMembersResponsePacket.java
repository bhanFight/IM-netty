package the.hb.protocol.response;

import lombok.Data;
import the.hb.protocol.Packet;
import the.hb.protocol.command.Command;

import java.util.List;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/8 17:42
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private boolean success;
    private List<String> groupMembers;
    private String message;

    public boolean isSuccess(){
        return success;
    }

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
