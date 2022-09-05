package the.hb.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import the.hb.attribute.Attributes;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/5 15:38
 */
public class LoginUtil {


    public static void markAsLogin(Channel channel) {

        channel.attr(Attributes.LOGIN).set(true);

    }

    public static boolean isLogin(Channel channel) {
        Attribute<Boolean> attr = channel.attr(Attributes.LOGIN);
        return attr.get() != null;
    }
}
