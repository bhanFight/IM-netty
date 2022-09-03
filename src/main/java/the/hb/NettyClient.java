package the.hb;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * <p>
 *
 * @author bHan        Email:1214599243@qq.com
 * <p>2022/9/3 15:24
 */
public class NettyClient {

    public static void main(String[] args) {
        NioEventLoopGroup workers = new NioEventLoopGroup();

        Bootstrap bootStrap = new Bootstrap();
        bootStrap
                .group(workers)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                    }
                });
        connect(bootStrap, "baidu.com", 80);

    }
    public static void connect(Bootstrap bootstrap, String host, int port){
        bootstrap.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("连接成功");
                }else{
                    System.out.println("连接失败");
                }
            }
        });
    }

}
