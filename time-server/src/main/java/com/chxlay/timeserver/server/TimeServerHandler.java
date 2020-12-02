package com.chxlay.timeserver.server;

import com.chxlay.common.constants.ResultEnum;
import com.chxlay.common.util.DateUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;

/**
 * @author Alay
 * @date 2020-12-02 10:59
 * @project netty
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 测试记录服务数
     */
    private int count;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("服务端数：" + ++count);
        String request = (String) msg;
        // 验证客户端发送来得请求信息,
        String response;
        if ("TIME_ORDER".equals(request)){
            response = DateUtil.toString(LocalDateTime.now(), DateUtil.YEAR_TO_S);
        }else {
            response = ResultEnum.ERROR_TOKEN.getMessage();
        }
        // 构建返回的数据,防止粘包，加入 System.getProperty("line.separator")
        byte[] buff = (response + System.getProperty("line.separator")).getBytes();
        ByteBuf buffer = Unpooled.copiedBuffer(buff);

        // 相应信息写入返回
        ctx.writeAndFlush(buffer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}