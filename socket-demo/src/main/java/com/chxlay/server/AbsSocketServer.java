package com.chxlay.server;

import ch.qos.logback.core.net.server.ServerListener;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PreDestroy;

/**
 * @author Alay
 * @date 2020-12-02 13:27
 * @project Braineex
 */
public abstract class AbsSocketServer {

    protected Logger logger = LoggerFactory.getLogger(ServerListener.class);
    protected final EventLoopGroup bossGroup;
    protected final EventLoopGroup workerGroup;
    protected final ServerBootstrap bootstrap;
    protected Channel channel;

    @Autowired
    protected NacosDiscoveryProperties nacosProperties;

    public AbsSocketServer() {
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
    }

    protected void shutdown() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    /**
     * 服务端启动的方法
     *
     * @throws InterruptedException
     */
    public abstract void start() throws InterruptedException;

    /**
     * 注册服务到注册中心
     */
    public abstract void registerNacos();

    /**
     * 关闭通道
     */
    @PreDestroy
    public void close() {
        channel.close();
        channel.parent().close();
    }
}