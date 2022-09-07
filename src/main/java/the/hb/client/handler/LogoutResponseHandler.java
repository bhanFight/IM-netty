package the.hb.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.hb.protocol.response.LogoutResponsePacket;
import the.hb.util.LoginUtil;

import java.util.Date;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/7 18:23
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) throws Exception {
        if(logoutResponsePacket.isSuccess()){
            LoginUtil.logout(ctx.channel());
        }else{
            System.out.println(new Date() + ":退出失败，请重试");
        }
    }
}
