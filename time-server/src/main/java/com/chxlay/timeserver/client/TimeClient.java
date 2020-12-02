package com.chxlay.timeserver.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * 非Spring使用测试
 *
 * @author Alay
 * @date 2020-12-02 11:55
 * @project netty
 */
public class TimeClient {


    public static void main(String[] args) throws InterruptedException {
        TimeClient timeClient = new TimeClient();
        timeClient.connect("localhost", 11000);
    }

    public void connect(String hostName, int port) throws InterruptedException {
        // 客户端只需要一个
        EventLoopGroup eventGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            // 配置解码器
                            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            channel.pipeline().addLast(new StringDecoder());
                            // 将自定义实现的拦截器加入Socket通道
                            channel.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            // 建立连接
            ChannelFuture future = bootstrap.connect(hostName, port);

            // 关闭连接通道
            future.channel().closeFuture().sync();
        } finally {
            // 关闭组
            eventGroup.shutdownGracefully();
        }
    }

}