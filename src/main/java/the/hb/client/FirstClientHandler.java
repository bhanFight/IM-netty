package the.hb.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.hb.protocol.request.LoginRequestPacket;
import the.hb.protocol.response.LoginResponsePacket;
import the.hb.protocol.Packet;
import the.hb.protocol.PacketCodeC;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/3 18:27
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("索隆");
        loginRequestPacket.setPassword("suoLong123..");

        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);
        System.out.println(new Date() + ":客户端发起登录请求");
        ctx.channel().writeAndFlush(buffer);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(buffer);

        if(packet instanceof LoginResponsePacket){
            System.out.println(new Date() + ":收到登录响应");
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if(loginResponsePacket.isSuccess()){
                System.out.println(new Date() + ":登录成功");
            }else{
                System.out.println(new Date() + ":登录失败:" + loginResponsePacket.getReason());
            }
        }

    }
    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        ByteBuf byteBuf = ctx.alloc().buffer();
        byte[] bytes = "你好，路飞".getBytes(Charset.forName("utf-8"));
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }
}
