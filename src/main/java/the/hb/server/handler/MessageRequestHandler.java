package the.hb.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.hb.protocol.Packet;
import the.hb.protocol.PacketCodeC;
import the.hb.protocol.request.MessageRequestPacket;
import the.hb.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/5 21:21
 */
public class MessageRequestHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packet packet = (Packet) msg;
        if(packet instanceof MessageRequestPacket){
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + ":服务器回复客户端发来的消息(" +
                    messageRequestPacket.getMessage() + "):");
            String message = "我在，有什么事情？";
            System.out.println(message);
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage(message);
            ctx.channel().writeAndFlush(messageResponsePacket);
        }
        super.channelRead(ctx, msg);
    }
}
