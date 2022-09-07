package the.hb.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.hb.Session.Session;
import the.hb.protocol.request.LoginRequestPacket;
import the.hb.protocol.response.LoginResponsePacket;
import the.hb.util.LoginUtil;
import the.hb.util.SessionUtil;

import java.util.Date;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/5 21:21
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println(new Date() + ":收到登录请求");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        if(validate(loginRequestPacket)){
            String userId = loginRequestPacket.getUserId();
            String userName = loginRequestPacket.getUserName();
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setUserId(userId);
        }else{
            System.out.println(new Date() + ":登录失败");
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("用户名或密码不存在");
        }

        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBoundSession(ctx.channel());
        super.channelInactive(ctx);
    }

    private boolean validate(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
