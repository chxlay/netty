package com.chxlay.chat.config;

import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Alay
 * @date 2020-12-01 14:38
 * @project netty-chat
 */
@Component
public class EventGroup {


    /**
     * 创建 Bean
     * destroyMethod 指定销毁类执行的方法
     *
     * @return
     */
    @Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        NioEventLoopGroup executors = new NioEventLoopGroup();
        return executors;
    }

    /**
     * 创建 Bean
     * destroyMethod 指定销毁类执行的方法
     *
     * @return
     */
    @Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        NioEventLoopGroup executors = new NioEventLoopGroup();
        return executors;
    }

}