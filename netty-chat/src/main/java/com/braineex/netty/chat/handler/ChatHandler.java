package com.braineex.netty.chat.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Component;

import java.net.SocketAddress;

/**
 * @author Alay
 * @date 2020-12-01 16:09
 * @project netty-chat
 */
@Component
// 多线程的环境下可用,可记录在线数
@ChannelHandler.Sharable
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 创建一个通道组
     */
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 广播方法
     *
     * @param ctx
     * @param text
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame text) {
        // 广播多人聊天
        String content = text.text();
        // 当前发送聊天的通道
        Channel iChannel = ctx.channel();
        for (Channel channel : channels) {
            if (channel == iChannel) {
                channel.writeAndFlush(new TextWebSocketFrame("我自己：" + content));
                continue;
            }
            SocketAddress address = ctx.channel().remoteAddress();
            channel.writeAndFlush(new TextWebSocketFrame(address + "发送信息：" + content));
        }
    }

    /**
     * 进入的方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        SocketAddress address = ctx.channel().remoteAddress();
        // 广播,让所有人知道有用户进入聊天室
        for (Channel channel : channels) {
            channel.writeAndFlush(new TextWebSocketFrame(address + "进入聊天室"));
        }
        // 将用户通道加入通道组
        channels.add(ctx.channel());
    }

    /**
     * 退出的方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        SocketAddress address = ctx.channel().remoteAddress();
        // 广播,让所有人知道用户退出聊天室
        for (Channel channel : channels) {
            channel.writeAndFlush(new TextWebSocketFrame(address + "退出聊天室"));
        }
        // 将用户通道移除通道组
        channels.remove(ctx.channel());
    }
}
