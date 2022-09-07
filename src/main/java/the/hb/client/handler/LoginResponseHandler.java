package the.hb.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.hb.protocol.request.LoginRequestPacket;
import the.hb.protocol.request.MessageRequestPacket;
import the.hb.protocol.response.LoginResponsePacket;
import the.hb.server.handler.MessageRequestHandler;
import the.hb.util.LoginUtil;

import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/5 20:29
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("索隆");
        loginRequestPacket.setPassword("suoLong123..");

        ctx.channel().writeAndFlush(loginRequestPacket);*/
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
//        System.out.println(new Date() + ":收到登录响应");

        if(loginResponsePacket.isSuccess()){
            System.out.println(new Date() + ":登录成功,您的userId为" + loginResponsePacket.getUserId());
            LoginUtil.markAsLogin(ctx.channel());
        }else{
            System.out.println(new Date() + ":登录失败:" + loginResponsePacket.getReason());
        }
    }
}
