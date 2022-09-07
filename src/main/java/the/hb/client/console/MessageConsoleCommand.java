package the.hb.client.console;

import io.netty.channel.Channel;
import the.hb.protocol.request.MessageRequestPacket;

import java.util.Date;
import java.util.Scanner;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/7 17:27
 */
public class MessageConsoleCommand implements ConsoleCommand{

    @Override
    public void execute(Scanner sc, Channel channel) {
        System.out.print("请输入收信人的userId ->");
        String toUserId = sc.nextLine();
        MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
        messageRequestPacket.setToUserId(toUserId);
        System.out.println("开始愉快的聊天吧！输入quit即可退出聊天");
        while(true){
            String message = sc.nextLine();
            if("quit".equals(message))
                break;
            messageRequestPacket.setMessage(message);
            channel.writeAndFlush(messageRequestPacket);
        }
    }
}
