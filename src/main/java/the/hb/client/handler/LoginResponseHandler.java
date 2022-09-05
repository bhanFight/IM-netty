package the.hb.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.hb.protocol.Packet;
import the.hb.protocol.request.LoginRequestPacket;
import the.hb.protocol.response.LoginResponsePacket;
import the.hb.util.LoginUtil;

import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/5 20:29
 */
public class LoginResponseHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("索隆");
        loginRequestPacket.setPassword("suoLong123..");

        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packet packet = (Packet) msg;
        if(packet instanceof LoginResponsePacket){
            System.out.println(new Date() + ":收到登录响应");
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if(loginResponsePacket.isSuccess()){
                System.out.println(new Date() + ":登录成功");
                LoginUtil.markAsLogin(ctx.channel());
            }else{
                System.out.println(new Date() + ":登录失败:" + loginResponsePacket.getReason());
            }
        }
        super.channelRead(ctx, msg);
    }
}
