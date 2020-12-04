package com.chxlay.server;

import com.chxlay.initializer.ProtoBufServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author Alay
 * @date 2020-12-04 23:49
 * @project netty
 */
public class ProtoBuffServer {
    private final int port;

    public ProtoBuffServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        new ProtoBuffServer(11000).start();
    }


    public void start() throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGoup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGoup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ProtoBufServerInitializer());

            bootstrap
                    .bind(port).sync()
                    .channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGoup.shutdownGracefully();
        }


    }
}
