package the.hb.protocol.command;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/3 20:56
 */
public interface Command {

    byte LOGIN_REQUEST = 1;
    byte LOGIN_RESPONSE = 2;
    byte MESSAGE_REQUEST = 3;
    byte MESSAGE_RESPONSE = 4;
    byte LOGOUT_REQUEST = 5;
    byte LOGOUT_RESPONSE = 6;
    byte CREATE_GROUP_REQUEST = 7;
    byte CREATE_GROUP_RESPONSE = 8;
    byte JOIN_GROUP_REQUEST = 9;
    byte JOIN_GROUP_RESPONSE = 10;
    byte QUIT_GROUP_REQUEST = 11;
    byte QUIT_GROUP_RESPONSE = 12;
}
