package com.chxlay.netty.bean;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * @author Alay
 * @date 2020-12-01 16:12
 * @project netty-chat
 */
public class TeacherWebSocketFrame extends WebSocketFrame {


    protected TeacherWebSocketFrame(ByteBuf binaryData) {
        super(binaryData);
    }

    protected TeacherWebSocketFrame(boolean finalFragment, int rsv, ByteBuf binaryData) {
        super(finalFragment, rsv, binaryData);
    }

    @Override
    public WebSocketFrame replace(ByteBuf byteBuf) {

        return null;
    }
}