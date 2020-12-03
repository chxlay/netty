package com.chxlay.heartbeat.Initializer;

import com.chxlay.heartbeat.handler.ServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author Alay
 * @date 2020-12-04 03:01
 * @project netty
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {

        ch.pipeline()
                // 处理 空闲状态监测的 Handler,读，写，全部，的时间触发空闲事件
                .addLast(new IdleStateHandler(1, 2, 3, TimeUnit.SECONDS))
                // 自定义的监测空闲状态的处理器
                .addLast(new ServerHandler());
    }
}
