package com.chxlay.chat.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alay
 * @date 2020-12-01 14:30
 * @project netty-chat
 */
@Component
public class ChatInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private WebSocketHandler webSocketHandler;
    @Autowired
    private ChatHandler chatHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) {

        socketChannel.pipeline()
                // 将一个请求，应答的消息编码，解码为一个Http 的消息
                .addLast(new HttpServerCodec())
                // 向客户端发送 HTML5 的文件
                .addLast(new ChunkedWriteHandler())
                // 将多条信息整合为一条,将一个Http消息尽心聚合
                .addLast(new HttpObjectAggregator(8192))
                // HttpRequestDecoder 和 HttpResponseEncoder 的组合,处理解码编码
                .addLast("httpServerCodec", new HttpServerCodec())
                // 创建握手操作
                .addLast(new WebSocketServerProtocolHandler("/websocket"))
                // 自定义的握手操作
                .addLast(webSocketHandler)

                // 将自定义的处理逻辑 Handler 加入
                .addLast(chatHandler);
    }
}