package the.hb.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import the.hb.Session.Session;
import the.hb.protocol.request.CreateGroupRequestPacket;
import the.hb.protocol.response.CreateGroupResponsePacket;
import the.hb.util.IDUtil;
import the.hb.util.SessionUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/8 0:10
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();

        String groupId = IDUtil.getUserId();
        ChannelGroup channels = new DefaultChannelGroup(ctx.executor());
        channels.add(ctx.channel());
        List<String> groupMembers = new ArrayList<>();
        Session session = SessionUtil.getSession(ctx.channel());
        groupMembers.add(session.getUserId() + " " + session.getUserName());

        for(String userId : userIdList){
            Channel channel = SessionUtil.getChannel(userId);
            if(channel != null){
                channels.add(channel);
                session = SessionUtil.getSession(channel);
                groupMembers.add(session.getUserId() + " " + session.getUserName());
            }
        }
        SessionUtil.bindChannelGroup(groupId, channels);
        System.out.println(new Date() + ":群聊创建成功，群的id为" + groupId +
                ",群成员有" + groupMembers);

        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setGroupMembers(groupMembers);
        channels.writeAndFlush(createGroupResponsePacket);
    }
}
