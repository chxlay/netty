package com.chxlay.handler;

import cn.hutool.json.JSONUtil;
import com.chxlay.entity.CallBackTalk;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Component;

/**
 * 回传信息的处理拦截器
 *
 * @author Alay
 * @date 2020-12-11 11:36
 * @project Braineex
 */
@Component
@ChannelHandler.Sharable
public class CallBackTalkHandler extends ChannelOutboundHandlerAdapter {


	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		if (msg instanceof CallBackTalk) {
			// 目前以JSON 字符串的形式传输数据
			String callBack = JSONUtil.toJsonStr(msg);
			msg = new TextWebSocketFrame(callBack);
		}
		super.write(ctx, msg, promise);
	}
}