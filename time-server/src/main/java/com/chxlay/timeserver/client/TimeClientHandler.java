package com.chxlay.timeserver.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Alay
 * @date 2020-12-02 12:01
 * @project netty
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    byte[] request;

    public TimeClientHandler() {
        // 初始化呢请求
        request = ("TIME_ORDER" + System.getProperty("line.separator")).getBytes();
    }

    /**
     * 客户端 与 服务端 TCP 链路以后调用此方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws InterruptedException {

        // 连接成功后将请求信息发送给服务端接收验证
        ByteBuf message;

        // 发送 多个连接请求
        for (int i = 0; i < 50; i++) {
            message = Unpooled.buffer(request.length);
            // 每个一秒发送一个请求
            Thread.sleep(1000);
            System.out.println("第 >> " + i + " << 个连接");
            message.writeBytes(request);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String content = (String) msg;
        System.out.println("当前时间:" + content);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}