package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author Alay
 * @date 2020-12-04 16:21
 * @project Braineex
 */
public class TeacherClient {
	private final String host;
	private final int port;


	public static void main(String[] args) throws InterruptedException {
		TeacherClient teacherClient = new TeacherClient("192.168.0.154", 11000);
		teacherClient.doConnect();
	}


	public TeacherClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void doConnect() throws InterruptedException {
		NioEventLoopGroup loopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(loopGroup)
					.channel(NioSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO))
					.handler(new ClientInitializer());
			Channel channel = bootstrap.connect(host, port).sync().channel();
			channel.closeFuture().sync();
		} finally {
			loopGroup.shutdownGracefully();
		}
	}
}