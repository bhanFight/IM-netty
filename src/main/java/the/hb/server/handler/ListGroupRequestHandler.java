package the.hb.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import the.hb.Session.Session;
import the.hb.protocol.request.ListGroupMembersRequestPacket;
import the.hb.protocol.response.ListGroupMembersResponsePacket;
import the.hb.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/8 17:51
 */
@ChannelHandler.Sharable
public class ListGroupRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    public static ListGroupRequestHandler INSTANCE = new ListGroupRequestHandler();

    private ListGroupRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket listGroupMembersRequestPacket) throws Exception {
        String groupId = listGroupMembersRequestPacket.getGroupId();

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        ListGroupMembersResponsePacket response = new ListGroupMembersResponsePacket();
        if(channelGroup != null){
            List<String> groupMembers = new ArrayList<>();
            Session session = new Session();
            for(Channel ch : channelGroup){
                session = SessionUtil.getSession(ch);
                groupMembers.add(session.getUserId() + " " + session.getUserName());
            }
            response.setSuccess(true);
            response.setGroupMembers(groupMembers);
        }else{
            response.setSuccess(false);
            response.setMessage("对不起，群组不存在！");
        }
        ctx.channel().writeAndFlush(response);
    }
}
