package the.hb.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.hb.Session.Session;
import the.hb.protocol.response.CreateGroupResponsePacket;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/8 0:42
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        String groupId = createGroupResponsePacket.getGroupId();
        List<String> groupMembers = createGroupResponsePacket.getGroupMembers();

        System.out.println(new Date() + ":群聊创建成功，群id为" + groupId + ",群里有" + groupMembers);
    }
}
