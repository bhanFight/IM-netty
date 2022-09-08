package the.hb.client.console;

import io.netty.channel.Channel;
import the.hb.protocol.command.Command;
import the.hb.protocol.request.ListGroupMembersRequestPacket;

import java.util.Scanner;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/8 17:47
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand{
    @Override
    public void execute(Scanner sc, Channel channel) {
        System.out.print("请出入群组id ->");
        String groupId = sc.nextLine();

        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();
        listGroupMembersRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupMembersRequestPacket);
    }
}
