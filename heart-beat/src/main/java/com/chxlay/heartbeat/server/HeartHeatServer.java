package com.chxlay.heartbeat.server;

import com.chxlay.heartbeat.Initializer.ServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * @author Alay
 * @date 2020-12-04 02:49
 * @project netty
 */
public class HeartHeatServer {
    private final int port;

    public HeartHeatServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        HeartHeatServer server = new HeartHeatServer(11989);
        server.start();
    }


    public void start() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    // 通过反射创建管道对象
                    .channel(NioServerSocketChannel.class)
                    // handler()针对的是 bossGroup 线程
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // childHandler 是针对于 workerGroup 起作用的
                    .childHandler(new ServerInitializer());

            // 端口地址绑定
            ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(port)).sync();
            channelFuture.channel().closeFuture().sync();


        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}