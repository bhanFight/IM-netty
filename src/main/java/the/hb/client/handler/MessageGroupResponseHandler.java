package the.hb.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.hb.protocol.response.MessageGroupResponsePacket;

import java.util.Date;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/8 19:51
 */
@ChannelHandler.Sharable
public class MessageGroupResponseHandler extends SimpleChannelInboundHandler<MessageGroupResponsePacket> {

    public static MessageGroupResponseHandler INSTANCE = new MessageGroupResponseHandler();

    private MessageGroupResponseHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageGroupResponsePacket responsePacket) throws Exception {
        if(responsePacket.isSuccess()){
            System.out.println(new Date() + ":[" + responsePacket.getFromUser() + "]" + responsePacket.getMessage());
        }else{
            System.out.println(new Date() + ":" + responsePacket.getWrongMessage());
        }
    }
}
