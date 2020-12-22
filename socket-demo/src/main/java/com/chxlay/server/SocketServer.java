package com.chxlay.server;

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
	@Value("${socketPort.tutorSocket}")
	private int port;
	@Autowired
	private ServerChannelInitializer serverChannelInitializer;

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

}