package the.hb.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import the.hb.Session.Session;
import the.hb.protocol.request.JoinGroupRequestPacket;
import the.hb.protocol.response.JoinGroupResponsePacket;
import the.hb.util.SessionUtil;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/8 14:41
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        String groupId = joinGroupRequestPacket.getGroupId();

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        JoinGroupResponsePacket joinGroupResponsePacketToOthers = new JoinGroupResponsePacket();

        if(channelGroup != null){
            joinGroupResponsePacket.setSuccess(true);
            joinGroupResponsePacketToOthers.setMessage("[" + SessionUtil.getSession(ctx.channel()).getUserName() +
                    "]加入群聊");
            channelGroup.writeAndFlush(joinGroupResponsePacketToOthers);
            channelGroup.add(ctx.channel());
            joinGroupResponsePacket.setMessage("加入群聊成功！");
            ctx.channel().writeAndFlush(joinGroupResponsePacket);
        }else{
            joinGroupResponsePacket.setSuccess(false);
            joinGroupResponsePacket.setMessage("对不起，没有找到群组，请重新输入");
            ctx.channel().writeAndFlush(joinGroupResponsePacket);
        }
    }
}
