package com.chxlay.handler;

import cn.hutool.json.JSONUtil;
import com.chxlay.entity.CallTalk;
import com.chxlay.talk.AbsTalkHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 家教答疑拦截器(再试以JSON字符串的形式传输,后续可更改为 protoBuf 或 thrift(推荐)传输)
 *
 * @author Alay
 * @date 2020-12-08 12:06
 * @project Braineex
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class TutorAnswerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	@Qualifier("headTalkHandler")
	@Autowired
	private AbsTalkHandler headTalkHandler;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame jsonStr) {
		if (!jsonStr.text().startsWith("{") || !jsonStr.text().endsWith("}")) {
			// 不是JSON CallTalk对象的字符串,不处理
			String text = jsonStr.text();
			if (HeartKeeper.PING.equals(text)) {
				return;
			}
			log.error(">{}<数据不是JSON数据", text);
			return;
		}
		CallTalk callTalk = JSONUtil.toBean(jsonStr.text(), CallTalk.class);
		headTalkHandler.messageReceived(ctx, callTalk);
	}

}