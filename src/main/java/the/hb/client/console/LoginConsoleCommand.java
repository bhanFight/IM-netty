package the.hb.client.console;

import io.netty.channel.Channel;
import the.hb.protocol.request.LoginRequestPacket;
import the.hb.util.IDUtil;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/7 17:02
 */
public class LoginConsoleCommand implements ConsoleCommand{
    @Override
    public void execute(Scanner sc, Channel channel) {
        System.out.print(new Date() + ":请输入用户名进行登录->");
        String userName = sc.nextLine();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setUserId(IDUtil.getUserId());
        loginRequestPacket.setUserName(userName);
        loginRequestPacket.setPassword("suoLong123..");

        channel.writeAndFlush(loginRequestPacket);

        waitForLoginResponse();
    }
    private static void waitForLoginResponse(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
