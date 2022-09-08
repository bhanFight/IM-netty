package the.hb.client.console;

import io.netty.channel.Channel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/7 16:56
 */
public class ConsoleCommandManager implements ConsoleCommand{

    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager(){
        consoleCommandMap = new HashMap<>();

        this
                .put("message", new MessageConsoleCommand())
                .put("createGroup", new CreateGroupConsoleCommand())
                .put("logout", new LogoutConsoleCommand())
                .put("createGroup", new CreateGroupConsoleCommand())
                .put("joinGroup", new JoinGroupConsoleCommand())
                .put("quitGroup", new QuitGroupConsoleCommand());
    }

    @Override
    public void execute(Scanner sc, Channel channel) {
//        System.out.print(new Date() + ":请出入指令->");

        String command = sc.nextLine();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if(consoleCommand != null){
            consoleCommand.execute(sc, channel);
        }else{
            System.out.println(new Date() + ":输入指令非法，请重新输入");
            execute(sc, channel);
        }
    }

    private ConsoleCommandManager put(String command, ConsoleCommand consoleCommandExecuter){
        consoleCommandMap.put(command, consoleCommandExecuter);
        return this;
    }

}
