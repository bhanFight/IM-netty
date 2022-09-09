package the.hb.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import the.hb.protocol.request.QuitGroupRequestPacket;
import the.hb.protocol.response.QuitGroupResponsePacket;
import the.hb.util.SessionUtil;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/8 15:39
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    private QuitGroupRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        String groupId = quitGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        if(channelGroup != null){
            channelGroup.remove(ctx.channel());
            quitGroupResponsePacket.setSuccess(true);
            if(channelGroup.size() == 0){
                SessionUtil.unBindChannelGroup(groupId);
                quitGroupResponsePacket.setMessage("退出群聊成功，群已经解散");
            }else{
                quitGroupResponsePacket.setMessage("退出群聊成功");
                QuitGroupResponsePacket quitGroupResponsePacketToOthers = new QuitGroupResponsePacket();
                quitGroupResponsePacketToOthers.setMessage("[" + SessionUtil.getSession(ctx.channel())
                        .getUserName() + "]退出群聊");
                channelGroup.writeAndFlush(quitGroupResponsePacketToOthers);
            }
        }else{
            quitGroupResponsePacket.setSuccess(false);
            quitGroupResponsePacket.setMessage("群不存在！");
        }
        ctx.channel().writeAndFlush(quitGroupResponsePacket);
    }
}
