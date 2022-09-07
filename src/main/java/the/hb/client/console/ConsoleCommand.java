package the.hb.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/7 16:53
 */
public interface ConsoleCommand {

    void execute(Scanner sc, Channel channel);

}
