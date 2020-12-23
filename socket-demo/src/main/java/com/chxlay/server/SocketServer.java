package com.chxlay.server;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.chxlay.initializer.ServerChannelInitializer;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Alay
 * @date 2020-12-03 18:55
 * @project Braineex
 */
@Component
public class SocketServer extends AbsSocketServer {
    /**
     * 绑定端口号
     */
    @Value("${nettyAddr.tutor.port}")
    private int port;
    @Value("${nettyAddr.tutor.serverName}")
    private String serverName;
    @Value("${nettyAddr.tutor.netIp}")
    private String netIp;

    @Autowired
    private ServerChannelInitializer serverChannelInitializer;

    /**
     * 启动 Netty 服务
     *
     * @throws InterruptedException
     */
    @Override
    public void start() throws InterruptedException {
        SocketServer socketServer = new SocketServer();
        try {
            bootstrap
                    .group(this.bossGroup, this.workerGroup)
                    // 日志级别
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 指定通道类型为NioServerSocketChannel
                    .channel(NioServerSocketChannel.class)
                    // 判断选择连接是WebSocket请求或者是TCP 连接请求的
                    .childHandler(serverChannelInitializer)

                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口
            ChannelFuture future = bootstrap.bind(port).sync();
            // 关闭通道
            future.channel().closeFuture();
        } finally {
            socketServer.shutdown();
        }
    }

    /**
     * 启动 Netty 服务后，必须将其注册到注册中心，Gateway 才能负载均衡路由到服务
     */
    @Override
    public void registerNacos() {
        try {
            //获取 Nacos 服务实例
            NamingService namingService = NamingFactory.createNamingService(nacosProperties.getServerAddr());
            logger.info("Get Nacos instance success");
            //将服务注册到注册中心
            namingService.registerInstance(serverName, netIp, port);
        } catch (NacosException e) {
            logger.error("REGISTER_NACOS_FAILED,errMsg:{},errCode:{}", e.getErrMsg(), e.getErrCode());
        }
    }

}