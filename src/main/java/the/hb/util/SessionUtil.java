package the.hb.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.Attribute;
import the.hb.Session.Session;
import the.hb.attribute.Attributes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/6 20:46
 */
public class SessionUtil {

    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();
    private static final Map<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {

        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
        System.out.println(new Date() + ":[" + session.getUserName() + "]已登陆");
    }

    public static boolean hasLogin(Channel channel){

        Attribute<Session> session = channel.attr(Attributes.SESSION);
        return session.get() != null;

    }

    public static Channel getChannel(String userId) {

        return userIdChannelMap.get(userId);

    }

    public static Session getSession(Channel channel) {

        return channel.attr(Attributes.SESSION).get();

    }

    public static void unBoundSession(Channel channel) {

        String userName = null;
        if(hasLogin(channel)){
            Session session = channel.attr(Attributes.SESSION).get();
            userName = session.getUserName();
            userIdChannelMap.remove(session.getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
        if(userName != null){
            System.out.println(new Date() + ":[" + userName + "]已退出");
        }
    }

    public static void bindChannelGroup(String groupId, ChannelGroup channels) {
        channelGroupMap.put(groupId, channels);
    }

    public static Session getSession(String userId) {
        return getSession(getChannel(userId));
    }
}
