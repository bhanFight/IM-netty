package the.hb.client.console;

import io.netty.channel.Channel;
import the.hb.protocol.request.MessageGroupRequestPacket;

import java.util.Scanner;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/8 19:30
 */
public class MessageGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void execute(Scanner sc, Channel channel) {
        System.out.println("请输入群组id和消息，以空格隔开 ->");
        String groupId = sc.next();
        String message = sc.next();

        MessageGroupRequestPacket requestPacket = new MessageGroupRequestPacket();
        requestPacket.setGroupId(groupId);
        requestPacket.setMessage(message);
        channel.writeAndFlush(requestPacket);
    }
}
