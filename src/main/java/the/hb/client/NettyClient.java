package the.hb.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import the.hb.client.handler.FirstClientHandler;
import the.hb.client.handler.LoginResponseHandler;
import the.hb.client.handler.MessageResponseHandler;
import the.hb.common.handler.PacketDecoder;
import the.hb.common.handler.PacketEncoder;
import the.hb.common.handler.Spliter;
import the.hb.protocol.PacketCodeC;
import the.hb.protocol.request.LoginRequestPacket;
import the.hb.protocol.request.MessageRequestPacket;
import the.hb.util.LoginUtil;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
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
                        socketChannel.pipeline()
                                .addLast(new Spliter())
                                .addLast(new PacketDecoder())
                                .addLast(new PacketEncoder())
                                .addLast(new LoginResponseHandler())
                                .addLast(new MessageResponseHandler());
                    }
                });
        connect(bootStrap, "localhost", 8081, MAX_RETRY);

    }
    public static void connect(Bootstrap bootstrap,  String host, int port, int retry){
        bootstrap.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("连接成功,启动控制台线程");
                    Channel channel = ((ChannelFuture) future).channel();
                    startConsoleThread(channel);
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

    private static void startConsoleThread(Channel channel) {

        new Thread(() ->{
            System.out.print("请输入用户名进行登录:");
            while(!Thread.currentThread().isInterrupted()){
                Scanner sc = new Scanner(System.in);
                if(LoginUtil.isLogin(channel)){
                    String toUserId = sc.next();
                    String message = sc.nextLine();

                    //发送message到服务器
                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                    messageRequestPacket.setToUserId(toUserId);
                    messageRequestPacket.setMessage(message);

//                    ByteBuf encode = PacketCodeC.INSTANCE.encode(channel.alloc(), messageRequestPacket);
                    channel.writeAndFlush(messageRequestPacket);
                }else{
                    String userName = sc.nextLine();
                    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

                    loginRequestPacket.setUserId(UUID.randomUUID().toString());
                    loginRequestPacket.setUserName(userName);
                    loginRequestPacket.setPassword("suoLong123..");

                    channel.writeAndFlush(loginRequestPacket);

                    waitForLoginResponse();
                }
            }
        }).start();

    }

    private static void waitForLoginResponse(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
