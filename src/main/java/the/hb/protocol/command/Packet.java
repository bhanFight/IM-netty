package the.hb.protocol.command;

import lombok.Data;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/3 20:58
 */
@Data
public abstract class Packet {

    private Byte version = 1;

    public abstract Byte getCommand();

}
