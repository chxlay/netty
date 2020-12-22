package client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author Alay
 * @date 2020-12-04 16:34
 * @project Braineex
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) {
		ch.pipeline()
				.addLast("chunkedWriteHandler", new ChunkedWriteHandler())
				.addLast(new ObjectEncoder())
				.addLast(new ObjectDecoder(1024, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())))
				.addLast(new ClientHandler());
	}
}