package the.hb;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/3 15:24
 */
public class NettyClient {
    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup workers = new NioEventLoopGroup();

        Bootstrap bootStrap = new Bootstrap();
        bootStrap
                .group(workers)
                .channel(NioSocketChannel.class)
                .attr(AttributeKey.newInstance("clientName"), "nettyClient")
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                    }
                });
        connect(bootStrap, "localhost", 8080, MAX_RETRY);

    }
    public static void connect(Bootstrap bootstrap,  String host, int port, int retry){
        bootstrap.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("连接成功");
                }else if(retry == 0){
                    System.out.println("重连次数已用完，连接失败");
                }else{
                    int order = MAX_RETRY - retry + 1;
                    int delay = 1 << order;
                    System.out.println(new Date() + ":连接失败，第" + order + "次重连");
                    bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1),
                            delay, TimeUnit.SECONDS);
                }
            }
        });
    }
}
