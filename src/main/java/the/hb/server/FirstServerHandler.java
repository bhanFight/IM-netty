package the.hb.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.hb.protocol.request.LoginRequestPacket;
import the.hb.protocol.response.LoginResponsePacket;
import the.hb.protocol.Packet;
import the.hb.protocol.PacketCodeC;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/3 18:45
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(buffer);

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

            ctx.channel().writeAndFlush(PacketCodeC.INSTANCE.
                    encode(ctx.alloc(), loginResponsePacket));
        }

    }

    private boolean validate(LoginRequestPacket loginRequestPacket) {
        return false;
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "你好，我是路飞，我已收到你的消息".getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);
        return buffer;
    }
}
