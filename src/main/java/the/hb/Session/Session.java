package the.hb.Session;

import lombok.Data;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/6 20:37
 */
@Data
public class Session {

    private String userId;
    private String userName;

    public Session(){
    }
    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
