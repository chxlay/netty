package com.braineex.netty.chat.bean;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * @author Alay
 * @date 2020-12-01 16:12
 * @project netty-chat
 */
public class TeacherWebSockerFrame extends WebSocketFrame {


    protected TeacherWebSockerFrame(ByteBuf binaryData) {
        super(binaryData);
    }

    protected TeacherWebSockerFrame(boolean finalFragment, int rsv, ByteBuf binaryData) {
        super(finalFragment, rsv, binaryData);
    }

    @Override
    public WebSocketFrame replace(ByteBuf byteBuf) {

        return null;
    }
}