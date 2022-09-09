package the.hb.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.hb.protocol.response.LoginResponsePacket;
import the.hb.util.LoginUtil;

import java.util.Date;
/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/5 20:29
 */
@ChannelHandler.Sharable
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    public static LoginResponseHandler INSTANCE = new LoginResponseHandler();

    private LoginResponseHandler(){}

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("索隆");
        loginRequestPacket.setPassword("suoLong123..");

        ctx.channel().writeAndFlush(loginRequestPacket);*/
        super.channelActive(ctx);
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
