package the.hb.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.hb.util.LoginUtil;

import java.util.Date;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/6 19:20
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //用户登录校验
        if(!LoginUtil.isLogin(ctx.channel())){
            ctx.channel().close();
        }else{
            ctx.channel().pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ":用户未登录，连接已关闭");
        super.channelInactive(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        if(ctx.channel().isActive()) {
            System.out.println(new Date() + ":登录成功，authHandler已经被移除");
        }
        super.handlerRemoved(ctx);
    }
}
