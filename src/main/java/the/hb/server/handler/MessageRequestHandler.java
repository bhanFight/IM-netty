package the.hb.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.hb.protocol.request.MessageRequestPacket;
import the.hb.protocol.response.MessageResponsePacket;
import the.hb.util.SessionUtil;

import java.util.Date;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/5 21:21
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static MessageRequestHandler INSTANCE = new MessageRequestHandler();

    private MessageRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        /*System.out.println(new Date() + ":服务器回复客户端发来的消息(" +
                messageRequestPacket.getMessage() + "):");
        String message = "我在，有什么事情？";
        System.out.println(message);*/
        String toUserId = messageRequestPacket.getToUserId();
        String message = messageRequestPacket.getMessage();

        Channel channel = SessionUtil.getChannel(toUserId);
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(SessionUtil.getSession(ctx.channel()).getUserId());
        messageResponsePacket.setMessage(message);
        channel.writeAndFlush(messageResponsePacket);
    }
}
