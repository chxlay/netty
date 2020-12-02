package com.chxlay.timeserver.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * 没用使用Spring注入测试
 *
 * @author Alay
 * @date 2020-12-02 10:53
 * @project netty
 */
public class TimeServer {

    public static void main(String[] args) throws InterruptedException {
        TimeServer timeServer = new TimeServer();
        timeServer.build(11000);
    }

    public void build(int port) throws InterruptedException {
        // 创建两个线程组

        // 客户端连接处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 进行 SocketChannel 网络读写
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 启动两组线程组
            ServerBootstrap serverBootstrap = bootstrap.group(bossGroup, workerGroup)
                    // 此类对应 JDK 类似 Nio 里的 ServerSocketChannel
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 配置解码器
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            socketChannel.pipeline().addLast(new StringDecoder());
                            // 将自定义实现的拦截器加入Socket通道,用于处理请求
                            socketChannel.pipeline().addLast(new TimeServerHandler());
                        }
                    /*
                    用来初始化服务端可连接队列，服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接,多个客户端来的时候,
                    服务端将不能处理的客户端连接请求放在队列中等待处理,backlog参数指定了队列的大小,BACKLOG用于构造服务端套接字ServerSocket对象,
                    标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50
                    */
                    }).childOption(ChannelOption.SO_BACKLOG, 1024)
                    /*
                    客户端Socket会每隔段的时间（大约两个小时）就会利用空闲的连接向服务器发送一个数据包。这个数据包并没有其它的作用，
                    只是为了检测一下服务器是否仍处于活动状态。如果服务器未响应这个数据包，在大约11分钟后，客户端Socket再发送一个数据包，
                    如果在12分钟内，服务器还没响应，那么客户端Socket将关闭。如果将Socket选项关闭，客户端Socket在服务器无效的情况下可能会长时间不会关闭
                    */
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定地址
            ChannelFuture future = serverBootstrap.bind(port).sync();

            // 关闭通道
            future.channel().closeFuture().sync();
        } finally {
            // 关闭组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}