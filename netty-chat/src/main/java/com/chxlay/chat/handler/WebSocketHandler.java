package com.chxlay.chat.handler;

import com.chxlay.common.constants.CommonConstants;
import com.chxlay.common.constants.RequestHeader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;
import io.netty.channel.ChannelHandler;

/**
 * @author Alay
 * @date 2020-12-01 14:40
 * @project netty-chat
 */
@Component
// 多线程的环境下可用,可记录在线数
@ChannelHandler.Sharable
public class WebSocketHandler extends ChannelInboundHandlerAdapter {

    /**
     * 如果客户端请求是 WebSocket 请求，此变量将自动填充值
     */
    private WebSocketServerHandshaker handshaker;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 页面向服务器发送WebSocket请求：先发送一条http请求，在请求头里携带 WebSocket 请求信息SecWebSocketKey,Upgrade
        // 判断是否是Http请求发起WebSocket握手请求
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            if (!request.decoderResult().isSuccess() &&
                    !CommonConstants.UPGRADE_WEBSOCKET.equals(request.headers().get(RequestHeader.UPGRADE))) {
                // 请求 WebSocket 验证不通过
                DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.BAD_REQUEST);
                if (response.status().code() != 200) {
                    ByteBuf byteBuf = Unpooled.copiedBuffer("请求异常", CharsetUtil.UTF_8);
                    response.content().writeBytes(byteBuf);
                    byteBuf.release();
                }
                ctx.writeAndFlush(response);
            }
            // WebSocket 请求验证通过

            // 创建 WebSocket 工厂示例,WebSocket连接 Url;
            WebSocketServerHandshakerFactory webSocketFactory =
                    new WebSocketServerHandshakerFactory("ws://127.0.0.1:8888/websocket", null, false);

            handshaker = webSocketFactory.newHandshaker(request);
            if (null == handshaker) {
                // 生成失败
                ChannelFuture channelFuture = WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            } else {
                // 将当前的 通道 和 Request 建立起连接
                handshaker.handshake(ctx.channel(), request);
            }
        } else if (msg instanceof WebSocketFrame) {
            // 发起的是 WebSocket 请求
            if (msg instanceof CloseWebSocketFrame) {
                // WebSocket 请求是关闭链路,直接关闭连接
                handshaker.close(ctx.channel(), (CloseWebSocketFrame) msg);
            }
            if (msg instanceof TextWebSocketFrame) {
                TextWebSocketFrame textSocket = (TextWebSocketFrame) msg;
                String content = textSocket.text();
                // 把数据会写回去
                ctx.writeAndFlush(new TextWebSocketFrame("serverContent" + msg));
            }
        }
    }
}