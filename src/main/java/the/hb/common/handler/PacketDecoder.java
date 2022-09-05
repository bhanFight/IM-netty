package the.hb.common.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import the.hb.protocol.Packet;
import the.hb.protocol.PacketCodeC;

import java.util.List;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/5 20:01
 */
public class PacketDecoder extends ByteToMessageDecoder {

    public static PacketDecoder packetDecoder = new PacketDecoder();

    private PacketDecoder(){}

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(PacketCodeC.INSTANCE.decode(byteBuf));
    }
}
