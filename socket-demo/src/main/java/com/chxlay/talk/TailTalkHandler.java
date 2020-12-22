package com.chxlay.talk;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

/**
 * 尾部拦截处理消息会话拦截处理:责任链模式 HeadTalkHandler ->TeacherTalk ->StudentTalk->TailTalkHandler)
 *
 * @author Alay
 * @date 2020-12-09 17:59
 * @project Braineex
 */
@Component
public class TailTalkHandler extends AbsTalkHandler {

	@Override
	public void offline(ChannelHandlerContext ctx) {
		// 移除用户通道 channelId  与 userId 的关系
		channelUserMap.remove(ctx.channel().id());
		// channelGroup是不需要关闭的,他监听着每一个组里的 channel 掉线的会自动移除
		ctx.channel().close();
	}
}