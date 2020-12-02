package com.chxlay.chat.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author Alay
 * @date 2020-12-01 15:37
 * @project netty-chat
 */
@Component
public class ChatServerApplication {

    @Autowired
    private ServerBootstrap bootstrap;

    private Channel channel;

    /**
     * 开始通道
     *
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        System.err.println("Netty 启动成功");
        ChannelFuture future = bootstrap
                // 绑定端口
                .bind(8888).sync()
                // 关闭通道
                .channel().closeFuture()
                .sync();
        this.channel = future.channel();
    }

    /**
     * 关闭通道
     */
    @PreDestroy
    public void close() {
        channel.close();
        channel.parent().close();
    }


}