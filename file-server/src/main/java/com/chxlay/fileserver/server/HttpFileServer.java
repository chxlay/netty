package com.chxlay.fileserver.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * 文件服务
 *
 * @author Alay
 * @date 2020-12-02 22:44
 * @project netty
 */
public class HttpFileServer {

    private final static int PORT = 12000;

    public static void main(String[] args) throws InterruptedException {
        HttpFileServer fileServer = new HttpFileServer();
        fileServer.start();
    }

    public void start() throws InterruptedException {
        // 客户端连接处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 进行 SocketChannel 网络读写
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 启动服务
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            // Http 请求解码器,FullHttpRequest的子类,Handler 中泛型会调用
                            socketChannel.pipeline().addLast("http-decoder", new HttpRequestDecoder());

                            // Http 响应编码器
                            socketChannel.pipeline().addLast("http-encoder", new HttpResponseEncoder());

                            // 消息解码器会将请求的消息分成多段，aggregator 将消息整合为一条，最大字节数
                            socketChannel.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));

                            // 配置握手拦截处理类
                            socketChannel.pipeline().addLast("httpServerHandler", new HttpFileServerHandler());

                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = bootstrap.bind(PORT).sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}