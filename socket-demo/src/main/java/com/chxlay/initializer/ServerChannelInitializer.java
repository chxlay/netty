package com.chxlay.initializer;

import com.chxlay.handler.AuthHandler;
import com.chxlay.handler.CallBackTalkHandler;
import com.chxlay.handler.HeartKeeper;
import com.chxlay.handler.TutorAnswerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Alay
 * @date 2020-12-04 18:08
 * @project Braineex
 */
@Component
@AllArgsConstructor
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final HeartKeeper heartKeeper;
    private final AuthHandler authHandler;
    private final TutorAnswerHandler tutorAnswerHandler;
    private final CallBackTalkHandler callbackHandler;
    // private final SslContext sslContext;
    // private final SSLEngine sslEngine;

    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline()
                // 路由处理
                // .addLast("route", routeHandler)
                // SSL 证书
                //.addLast("ssl", sslContext.newHandler(ch.alloc()))
                // 读写空闲事件处理
                .addLast(new IdleStateHandler(HeartKeeper.READER_IDLE_TIME_SECOND,
                        HeartKeeper.WRITER_IDLE_TIME_SECOND,
                        HeartKeeper.ALL_IDLE_TIME_SECOND, TimeUnit.SECONDS))
                // 自定义心跳判定机制
                .addLast("heartKeeper", heartKeeper)
                // HttpRequestDecoder 和 HttpResponseEncoder 的组合,处理解码编码
                .addLast("httpServerCodec", new HttpServerCodec())

                // ChunkedWriteHandler：向客户端发送HTML5文件,文件过大会将内存撑爆
                .addLast("chunkedWriteHandler", new ChunkedWriteHandler())

                // HttpObjectAggregator：将HTTP消息的多个部分合成一条完整的HTTP消息
                .addLast("aggregator", new HttpObjectAggregator(8192))

                .addLast("webSocketAggregator", new WebSocketFrameAggregator(65535))

                .addLast(new LengthFieldBasedFrameDecoder(1024 * 1024, 2, 2, 6, 10, false))

                // 对象解码
                .addLast("objectDecoder", new ObjectDecoder(1024 * 1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())))
                // 对象编码
                .addLast("objectEncoder", new ObjectEncoder())

                // 自定义Http请求拦截器
                .addLast("authHandler", authHandler)
                // 家教答疑拦截器
                .addLast("tutorAnswerHandler", tutorAnswerHandler)
                // 返回数据处理器
                .addLast("callbackHandler", callbackHandler);
    }
}