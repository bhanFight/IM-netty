package the.hb.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.hb.protocol.Packet;
import the.hb.protocol.PacketCodeC;
import the.hb.protocol.request.LoginRequestPacket;
import the.hb.protocol.response.LoginResponsePacket;

import java.util.Date;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/5 21:21
 */
public class LoginRequestHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packet packet = (Packet) msg;
        if(packet instanceof LoginRequestPacket){
            System.out.println(new Date() + ":收到登录请求");
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(loginRequestPacket.getVersion());
            if(validate(loginRequestPacket)){
                System.out.println(new Date() + ":用户" +
                        loginRequestPacket.getUserName() + "登录成功");
                loginResponsePacket.setSuccess(true);
            }else{
                System.out.println(new Date() + "登录失败");
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("用户名或密码不存在");
            }

            ctx.channel().writeAndFlush(loginResponsePacket);
        }
        super.channelRead(ctx, msg);
    }
    private boolean validate(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
