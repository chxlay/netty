package com.chxlay.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.stereotype.Component;

/**
 * 连接心跳检查
 *
 * @author Alay
 * @date 2020-12-05 11:20
 * @project Braineex
 */
@Component
@ChannelHandler.Sharable
public class HeartKeeper extends AbsChannelHandler {

	/**
	 * 读空闲判定判定死亡时长
	 */
	public static final int READER_IDLE_TIME_SECOND = 60;
	/**
	 * 写空闲判定判定死亡时长
	 */
	public static final int WRITER_IDLE_TIME_SECOND = 300;
	/**
	 * 全空闲判定死亡时长
	 */
	public static final int ALL_IDLE_TIME_SECOND = 1800;

	public static final String PING = "ping";

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			IdleState state = event.state();
			if (state == IdleState.READER_IDLE) {
				// 读空闲
			} else if (state == IdleState.WRITER_IDLE) {
				// 写空闲
			} else if (state == IdleState.ALL_IDLE) {
				// 读写都空闲,判定为下线
				Channel channel = ctx.channel();
				// 长时间未活动,强制下线,关闭掉线的用户通道
				channel.close();
				logger.error("close channel,channelId =  {}", channel.id().asLongText());
			}
		}
	}

}
