package the.hb.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import sun.java2d.pipe.SpanShapeRenderer;
import the.hb.protocol.request.LogoutRequestPacket;
import the.hb.protocol.response.LogoutResponsePacket;
import the.hb.util.SessionUtil;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/7 18:07
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket logoutRequestPacket) throws Exception {
        SessionUtil.unBoundSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
