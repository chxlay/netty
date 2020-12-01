package com.braineex.netty.chat.handler;

import com.braineex.netty.chat.bean.TeacherWebSockerFrame;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Alay
 * @date 2020-12-01 16:13
 * @project netty-chat
 */
public class TeacherHandler extends SimpleChannelInboundHandler<TeacherWebSockerFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TeacherWebSockerFrame teacherWebSockerFrame) throws Exception {

    }
}