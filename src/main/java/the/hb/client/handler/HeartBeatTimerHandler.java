package the.hb.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.hb.protocol.request.HeartBeatRequestPacket;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/9 20:58
 */
@ChannelHandler.Sharable
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    public static HeartBeatTimerHandler INSTANCE = new HeartBeatTimerHandler();

    private static final int HEARTBEAT_INTERVAL = 5;

    private HeartBeatTimerHandler(){}

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {

        ctx.executor().schedule(new Runnable() {
            @Override
            public void run() {
                if(ctx.channel().isActive()){
                    ctx.channel().writeAndFlush(new HeartBeatRequestPacket());
                    scheduleSendHeartBeat(ctx);
                }
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);

    }

}
