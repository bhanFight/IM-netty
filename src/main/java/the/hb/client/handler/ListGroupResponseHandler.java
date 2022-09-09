package the.hb.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.hb.protocol.response.ListGroupMembersResponsePacket;

import java.util.Date;
/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/8 18:07
 */
@ChannelHandler.Sharable
public class ListGroupResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {

    public static ListGroupResponseHandler INSTANCE = new ListGroupResponseHandler();

    private ListGroupResponseHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket responsePacket) throws Exception {
        if(responsePacket.isSuccess()){
            System.out.println(new Date() + ":群成员有 ->" + responsePacket.getGroupMembers());
        }else{
            System.out.println(new Date() + ":" + responsePacket.getMessage());
        }
    }
}
