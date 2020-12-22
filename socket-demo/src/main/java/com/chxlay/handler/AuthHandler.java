package com.chxlay.handler;

import cn.hutool.core.util.StrUtil;
import com.chxlay.common.exception.MyException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

/**
 * 处理Http请求的拦截器,请求认证认证拦截器
 * 还负责处理上线离线处理
 *
 * @author Alay
 * @date 2020-12-03 22:45
 * @project Braineex
 */
@Component
@ChannelHandler.Sharable
public class AuthHandler extends AbsChannelHandler {
	/**
	 * 客户端Http请求转WebSocket请求
	 * 如果客户端请求是 WebSocket 请求，此变量将自动填充值
	 */
	private WebSocketServerHandshaker handShaker;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws MyException {
		// 判定请求是否是 Http
		if (msg instanceof HttpRequest) {
			HttpRequest request = (HttpRequest) msg;
			// 请求是否带有用户 Token
			boolean hasToken = this.hasToken(ctx, request);
			if (hasToken) {
				// 非匿名请求
				String host = request.headers().getAsString(HttpHeaderNames.HOST);
				String uri = request.uri();
				WebSocketServerHandshakerFactory socketFactory =
						new WebSocketServerHandshakerFactory("wss://" + host + uri, null, false);

				handShaker = socketFactory.newHandshaker(request);
			} else {
				// 匿名请求
				this.sendAndClose(ctx, "请登录", HttpResponseStatus.NON_AUTHORITATIVE_INFORMATION);
			}
			// 握手成功
			if (null != handShaker) {
				// 将当前的 通道 和 Request 建立起连接
				ChannelFuture handshake = handShaker.handshake(ctx.channel(), request);
				if (handshake.isSuccess()) {
					logger.info("channelId: {},成功连接", handshake.channel().id().asLongText());
				}
			} else {
				// 握手失败,500
				this.sendAndClose(ctx, null, HttpResponseStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			// 对事件进行继续传播,知道完成 WebSocket 连接
			ctx.fireChannelRead(msg);
		}
	}

	/**
	 * 离线处理
	 *
	 * @param ctx
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws MyException {
		headTalkHandler.offline(ctx);
	}

	/**
	 * 异常处理
	 *
	 * @param ctx
	 * @param cause
	 * @throws Exception
	 */
	@Override
	public final void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws MyException {
		logger.warn(cause.getMessage());
		headTalkHandler.offline(ctx);
		ctx.channel().close();
	}

	/**
	 * 请求是否含有 Token
	 *
	 * @param request
	 * @return
	 */
	protected boolean hasToken(ChannelHandlerContext ctx, HttpRequest request) throws MyException {
		/*
		// 用户token -->>  Authorization: Bearer 3ddf4440-a88a-4161-bf12-11ad620f9cc0
		String uri = request.uri();
		try {
			uri = URLDecoder.decode(uri, CharsetUtil.UTF_8.displayName());
			request.setUri(uri);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (!uri.contains(CommonConstants.AUTHORIZATION_HEADER)) {
			logger.error("请求未认证token");
			return false;
		}
		int i = uri.indexOf(CommonConstants.AUTHORIZATION_HEADER);
		String token = StrUtil.subWithLength(uri, i + 21, 43);
		if (token.length() != CommonConstants.TOKEN_LENGTH) {
			logger.error("token: {} 无效", token);
			return false;
		}
		logger.info("token: {}", token);
		// 验证令牌信息
		OAuth2Authentication auth2Authentication = tokenService.loadAuthentication(token);
		PigxUser user = SecurityUtils.getUser(auth2Authentication);
		if (null == user) {
			// token无效
			logger.error("token: {} 无效", token);
			return false;
		}
		// 分配用户上线通道处理上线
		headTalkHandler.onlineAdapter(ctx, user.getId().toString(), uri);
		// 认证通过,将用户和通道建立关系
		*/
		return true;
	}

	/**
	 * 发送消息给客户端,并关闭用户连接
	 *
	 * @param ctx
	 * @param responseMsg
	 * @param status
	 */
	protected void sendAndClose(ChannelHandlerContext ctx, String responseMsg, HttpResponseStatus status) {
		ByteBuf byteBuf = null;
		int contentLength = 0;
		if (StrUtil.isNotEmpty(responseMsg)) {
			byteBuf = Unpooled.copiedBuffer(responseMsg, CharsetUtil.UTF_8);
			contentLength = byteBuf.readableBytes();
		}
		// 向客户端响应数据会写
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, byteBuf);

		// 响应头信息
		response.headers()
				.set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN)
				.set(HttpHeaderNames.CONTENT_LENGTH, contentLength);

		// 发送响应数据
		ChannelFuture channelFuture = ctx.writeAndFlush(response);

		// 手动的关闭连接（服务器端主动的关闭）ctx.channel().close();
		channelFuture.addListener(ChannelFutureListener.CLOSE);
	}

}