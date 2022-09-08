package the.hb.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import the.hb.Session.Session;
import the.hb.protocol.request.MessageGroupRequestPacket;
import the.hb.protocol.response.MessageGroupResponsePacket;
import the.hb.util.SessionUtil;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/8 19:35
 */
public class MessageGroupRequestHandler extends SimpleChannelInboundHandler<MessageGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageGroupRequestPacket requestPacket) throws Exception {
        String groupId = requestPacket.getGroupId();
        String message = requestPacket.getMessage();
        MessageGroupResponsePacket responsePacket = new MessageGroupResponsePacket();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        if(channelGroup != null){
            Session session = SessionUtil.getSession(ctx.channel());
            responsePacket.setSuccess(true);
            responsePacket.setMessage(message);
            responsePacket.setFromUser(session.getUserId() + " " + session.getUserName());
            channelGroup.writeAndFlush(responsePacket);
        }else{
            responsePacket.setSuccess(false);
            responsePacket.setWrongMessage("抱歉，群组不存在！");
            ctx.channel().writeAndFlush(responsePacket);
        }
    }
}
