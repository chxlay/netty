package com.chxlay.chat.server;

import com.chxlay.chat.handler.ChatInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Alay
 * @date 2020-12-01 14:01
 * @project netty-chat
 */
@Component
public class ChatServer {
    @Autowired
    private ChatInitializer chatInitializer;
    @Autowired
    private NioEventLoopGroup bossGroup;
    @Autowired
    private NioEventLoopGroup workGroup;


    /**
     * 配置Netty的启动类
     *
     * @return
     */
    @Bean
    public ServerBootstrap bootstrap() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                // 可以直接匿名内部类实现 chatInitializer
                .childHandler(chatInitializer)
                .option(ChannelOption.SO_BACKLOG, 100)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        return serverBootstrap;
    }
}