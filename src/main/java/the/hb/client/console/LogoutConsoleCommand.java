package the.hb.client.console;

import io.netty.channel.Channel;
import the.hb.protocol.request.LogoutRequestPacket;

import java.util.Scanner;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/7 17:03
 */
public class LogoutConsoleCommand implements ConsoleCommand{
    @Override
    public void execute(Scanner sc, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
