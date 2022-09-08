package the.hb.client.console;

import io.netty.channel.Channel;
import the.hb.protocol.request.QuitGroupRequestPacket;

import java.util.Scanner;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/8 15:35
 */
public class QuitGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void execute(Scanner sc, Channel channel) {
        System.out.print("请输入要退出的群id ->");
        String groupId = sc.nextLine();

        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
