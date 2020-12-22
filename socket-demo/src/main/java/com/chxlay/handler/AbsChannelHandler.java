package com.chxlay.handler;

import ch.qos.logback.core.net.server.ServerListener;
import com.chxlay.talk.AbsTalkHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Alay
 * @date 2020-12-03 17:07
 * @project Braineex
 */
@ChannelHandler.Sharable
public abstract class AbsChannelHandler extends ChannelInboundHandlerAdapter {
	@Qualifier("headTalkHandler")
	@Autowired
	protected AbsTalkHandler headTalkHandler;
	/**
	 * 日志打印提示
	 */
	protected Logger logger = LoggerFactory.getLogger(ServerListener.class);
}