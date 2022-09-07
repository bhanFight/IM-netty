package the.hb.client.console;

import io.netty.channel.Channel;
import the.hb.protocol.request.CreateGroupRequestPacket;
import the.hb.util.SessionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/7 17:03
 */
public class CreateGroupConsoleCommand implements ConsoleCommand{

    private static final String USER_ID_SPLITER = ",";

    @Override
    public void execute(Scanner sc, Channel channel) {
        System.out.print("【拉入群聊】请输入要拉入群聊的成员id，以" + USER_ID_SPLITER +"隔开：");
        String[] userIdList = sc.nextLine().split(USER_ID_SPLITER);
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIdList));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
