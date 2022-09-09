package the.hb.client.console;

import io.netty.channel.Channel;
import the.hb.protocol.request.JoinGroupRequestPacket;

import java.util.Scanner;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/8 14:37
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void execute(Scanner sc, Channel channel) {
        System.out.print("请输入要加入的群id ->");
        String groupId = sc.nextLine();

        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();
        joinGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
