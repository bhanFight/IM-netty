package the.hb.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import the.hb.common.handler.*;
import the.hb.server.handler.*;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/3 14:11
 */
public class NettyServer {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup workers = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boss, workers)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline()
                                .addLast(new IMIdleStateHandler())
                                .addLast(new Spliter())
                                .addLast(PacketCodecHandler.INSTANCE)
                                .addLast(LoginRequestHandler.INSTANCE)
                                .addLast(HeartBeatRequestHandler.INSTANCE)
                                .addLast("authHandler", AuthHandler.INSTANCE)
                                .addLast(MessageRequestHandler.INSTANCE)
                                .addLast(LogoutRequestHandler.INSTANCE)
                                .addLast(CreateGroupRequestHandler.INSTANCE)
                                .addLast(JoinGroupRequestHandler.INSTANCE)
                                .addLast(QuitGroupRequestHandler.INSTANCE)
                                .addLast(ListGroupRequestHandler.INSTANCE)
                                .addLast(MessageGroupRequestHandler.INSTANCE);
                    }
                });
        bind(serverBootstrap, 8080);
    }
    public static void bind(final ServerBootstrap serverBootstrap, final int port){
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("??????[" + port + "]????????????");
                }else{
                    System.out.println("??????[" + port + "]????????????");
                    bind(serverBootstrap, port+1);
                }
            }
        });
    }
}
