package the.hb.util;

import java.util.UUID;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/7 23:07
 */
public class IDUtil {

    public static String getUserId(){
        return UUID.randomUUID().toString().split("-")[0];
    }

}
