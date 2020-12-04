package com.chxlay.client;

import com.chxlay.initializer.ProtoBufClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author Alay
 * @date 2020-12-05 00:12
 * @project netty
 */
public class ProtoBufClient {

    private final String host;
    private final int port;

    public ProtoBufClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        ProtoBufClient client = new ProtoBufClient("localhost", 11000);
        client.doConnect();

    }

    public void doConnect() throws InterruptedException {
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .handler(new ProtoBufClientInitializer());

            bootstrap
                    .connect(host, port).sync()
                    .channel().closeFuture().sync();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }


}